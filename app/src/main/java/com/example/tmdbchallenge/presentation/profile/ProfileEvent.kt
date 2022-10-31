package com.example.tmdbchallenge.presentation.profile

sealed class ProfileEvent {
    object GetFavorites : ProfileEvent()
    object ShowLogoutDialog : ProfileEvent()
    data class OnLogoutDialogPress(val submit: Boolean) : ProfileEvent()
}