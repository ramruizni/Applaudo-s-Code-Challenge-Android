package com.example.tmdbchallenge.presentation.show_details

sealed class ShowDetailsEvent {
    object ToggleFavorite : ShowDetailsEvent()
}