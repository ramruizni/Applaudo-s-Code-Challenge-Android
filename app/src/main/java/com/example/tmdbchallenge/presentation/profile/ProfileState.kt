package com.example.tmdbchallenge.presentation.profile

import com.example.tmdbchallenge.domain.model.Show

data class ProfileState(
    val favorites: List<Show> = emptyList(),
    val showLogoutDialog: Boolean = false,
    val isLoading: Boolean = false
)