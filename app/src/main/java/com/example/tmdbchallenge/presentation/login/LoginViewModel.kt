package com.example.tmdbchallenge.presentation.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbchallenge.commons.Constants.SHARED_PREF_IS_LOGGED_IN
import com.example.tmdbchallenge.domain.use_case.login.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases,
    private val preferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is LoginEvent.PasswordChanged -> {
                _state.value = state.value.copy(
                    password = event.password,
                    passwordError = null
                )
            }
            is LoginEvent.AcceptedTerms -> {
                _state.value = _state.value.copy(
                    acceptedTerms = event.acceptedTerms,
                    acceptedTermsError = null
                )
            }
            is LoginEvent.Submit -> {
                submitForm()
            }
        }
    }

    private fun submitForm() {
        val emailResult = useCases.validateEmail(_state.value.email)
        val passwordResult = useCases.validatePassword(_state.value.password)
        val termsResult = useCases.validateTerms(_state.value.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            termsResult
        ).any { !it.isSuccessful }

        if (hasError) {
            _state.value = _state.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                acceptedTermsError = termsResult.errorMessage
            )
            return
        }

        preferences.edit().putBoolean(SHARED_PREF_IS_LOGGED_IN, true).apply()

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
        // TODO: Show login toast
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}