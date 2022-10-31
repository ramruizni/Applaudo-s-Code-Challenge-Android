package com.example.tmdbchallenge.presentation.show_list

import com.example.tmdbchallenge.domain.ShowFilter

sealed class ShowListEvent {
    data class OnFirstVisibleListIndex(val index: Int) : ShowListEvent()
    data class FilterChanged(val filter: ShowFilter) : ShowListEvent()
    data class QueryChanged(val query: String?) : ShowListEvent()

    object RetryAfterFailure: ShowListEvent()
}