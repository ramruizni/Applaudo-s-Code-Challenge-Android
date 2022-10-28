package com.example.tmdbchallenge.presentation.show_list

import com.example.tmdbchallenge.domain.ShowFilter
import com.example.tmdbchallenge.domain.model.Show

data class ShowListState(
    val showFilter: ShowFilter = ShowFilter.TOP_RATED,
    val showNameQuery: String? = null,
    val shows: List<Show> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)