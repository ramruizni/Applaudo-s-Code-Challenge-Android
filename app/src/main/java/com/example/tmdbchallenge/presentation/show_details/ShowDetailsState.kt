package com.example.tmdbchallenge.presentation.show_details

import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.domain.model.Show

data class ShowDetailsState(
    val show: Show? = null,
    val seasons: List<Season> = emptyList(),
    val isLoading: Boolean = false,

    val errorTriggered: Boolean = false,
    val functionToRetry: (() -> Unit)? = null,
)