package com.example.tmdbchallenge.presentation.profile.edit_profile

data class EditProfileState(
    val showEditDialog: Boolean = false,

    val name: String = "",
    val nameError: String? = null,
    val socialNetwork: String = "",
    val socialNetworkError: String? = null,

    val savedName: String = "",
    val savedSocialNetwork: String = ""
)