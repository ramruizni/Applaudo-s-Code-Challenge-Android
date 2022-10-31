package com.example.tmdbchallenge.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.use_case.profile.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: ProfileUseCases
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    private val channel = Channel<ProfileEvent>()
    val event = channel.receiveAsFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetFavorites -> {
                getFavorites()
            }
            is ProfileEvent.ShowLogoutDialog -> {
                state = state.copy(showLogoutDialog = true)
            }
            is ProfileEvent.OnLogoutDialogPress -> {
                state = state.copy(showLogoutDialog = false)
                viewModelScope.launch {
                    channel.send(ProfileEvent.OnLogoutDialogPress(event.submit))
                }
            }
        }
    }

    private fun getFavorites() {
        viewModelScope.launch {
            useCases
                .getFavorites()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { favorites ->
                                state = state.copy(favorites = favorites)
                            }
                        }
                        is Resource.Error -> {
                            // TODO: Propagate error to view
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

}