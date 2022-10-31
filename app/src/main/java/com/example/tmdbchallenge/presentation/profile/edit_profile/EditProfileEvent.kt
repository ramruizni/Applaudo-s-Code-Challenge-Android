package com.example.tmdbchallenge.presentation.profile.edit_profile

sealed class EditProfileEvent {
    object ShowEditDialog : EditProfileEvent()

    data class NameChanged(val name: String) : EditProfileEvent()
    data class SocialNetworkChanged(val socialNetwork: String) : EditProfileEvent()

    object Dismiss : EditProfileEvent()
    object Submit : EditProfileEvent()
}