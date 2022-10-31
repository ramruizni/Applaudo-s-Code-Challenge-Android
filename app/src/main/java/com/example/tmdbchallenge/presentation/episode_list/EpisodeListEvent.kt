package com.example.tmdbchallenge.presentation.episode_list

sealed class EpisodeListEvent {
    object RetryAfterFailure : EpisodeListEvent()
}