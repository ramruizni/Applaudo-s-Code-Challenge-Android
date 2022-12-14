package com.example.tmdbchallenge.presentation.login

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbchallenge.commons.Constants.SHARED_PREF_IS_LOGGED_IN
import com.example.tmdbchallenge.domain.use_case.login.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases,
    private val preferences: SharedPreferences
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    passwordError = null
                )
            }
            is LoginEvent.AcceptedTerms -> {
                state = state.copy(
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
        val emailResult = useCases.validateEmail(state.email)
        val passwordResult = useCases.validatePassword(state.password)
        val termsResult = useCases.validateTerms(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            termsResult
        ).any { !it.isSuccessful }

        if (hasError) {
            state = state.copy(
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
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}