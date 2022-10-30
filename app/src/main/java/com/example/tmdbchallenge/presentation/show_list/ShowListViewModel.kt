package com.example.tmdbchallenge.presentation.show_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.domain.use_case.show_list.ShowListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowListViewModel @Inject constructor(
    private val useCases: ShowListUseCases
) : ViewModel() {

    var state by mutableStateOf(ShowListState())
        private set

    init {
        getShows(page = 1)
    }

    fun onEvent(event: ShowListEvent) {
        when (event) {
            is ShowListEvent.OnFirstVisibleListIndex -> {
                val lastIndex = state.shows.last().id
                // TODO: Remove magic numbers for Page Size and Total Elements
                if (lastIndex > 2632) return
                if (!state.isRefreshing && event.index == lastIndex - 10) {
                    state = state.copy(isRefreshing = true)
                    getShows(page = (lastIndex / 20) + 1)
                }
            }
            is ShowListEvent.QueryChanged -> {
                state = state.copy(showNameQuery = event.query)
            }
            is ShowListEvent.FilterChanged -> {
                state = state.copy(showFilter = event.filter)
            }
        }
    }

    private fun getShows(page: Int) {
        viewModelScope.launch {
            if (state.showNameQuery.isNullOrEmpty()) {
                useCases
                    .getShowsByFilter(state.showFilter, page)
                    .collect { collectGetShowsResult(it) }
            } else {
                useCases
                    .getShowsByName(state.showNameQuery!!, page)
                    .collect { collectGetShowsResult(it) }
            }
        }
    }

    private fun collectGetShowsResult(result: Resource<List<Show>>) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { shows ->
                    state = state.copy(shows = shows)
                }
            }
            is Resource.Error -> {
                // TODO: Propagate error to view
            }
            is Resource.Loading -> {
                state = state.copy(isLoading = result.isLoading)
            }
        }
    }
}