package com.example.tmdbchallenge.presentation.episode_list

import com.example.tmdbchallenge.domain.model.Episode
import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.domain.model.Show

data class EpisodeListState(
    val show: Show? = null,
    val season: Season? = null,
    val episodes: List<Episode> = emptyList(),
    val isLoading: Boolean = false,

    val errorTriggered: Boolean = false,
    val functionToRetry: (() -> Unit)? = null,
)