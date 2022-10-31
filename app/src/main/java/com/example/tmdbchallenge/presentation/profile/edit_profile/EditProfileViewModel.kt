package com.example.tmdbchallenge.presentation.profile.edit_profile

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tmdbchallenge.commons.Constants.SHARED_PREF_USER_NAME
import com.example.tmdbchallenge.commons.Constants.SHARED_PREF_USER_SOCIAL_NETWORK
import com.example.tmdbchallenge.domain.use_case.profile.edit_profile.EditProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val useCases: EditProfileUseCases,
    private val preferences: SharedPreferences
) : ViewModel() {

    var state by mutableStateOf(EditProfileState())
        private set

    init {
        loadSavedInformation()
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.ShowEditDialog -> {
                state = state.copy(showEditDialog = true)
            }
            is EditProfileEvent.NameChanged -> {
                state = state.copy(
                    name = event.name,
                    nameError = null
                )
            }
            is EditProfileEvent.SocialNetworkChanged -> {
                state = state.copy(
                    socialNetwork = event.socialNetwork,
                    socialNetworkError = null
                )
            }
            is EditProfileEvent.Dismiss -> {
                state = EditProfileState()
                loadSavedInformation()
            }
            is EditProfileEvent.Submit -> {
                submitForm()
            }
        }
    }

    private fun submitForm() {
        val nameResult = useCases.validateName(state.name)
        val socialNetworkResult = useCases.validateSocialNetwork(state.socialNetwork)

        val hasError = listOf(
            nameResult,
            socialNetworkResult
        ).any { !it.isSuccessful }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                socialNetworkError = socialNetworkResult.errorMessage,
            )
            return
        }

        preferences.edit().putString(SHARED_PREF_USER_NAME, state.name).apply()
        preferences.edit().putString(SHARED_PREF_USER_SOCIAL_NETWORK, state.socialNetwork)
            .apply()

        state = EditProfileState()
        loadSavedInformation()
    }

    private fun loadSavedInformation() {
        val savedName = preferences.getString(SHARED_PREF_USER_NAME, "")
        val savedSocialNetwork = preferences.getString(SHARED_PREF_USER_SOCIAL_NETWORK, "")
        state = state.copy(savedName = savedName!!, savedSocialNetwork = savedSocialNetwork!!)
    }
}