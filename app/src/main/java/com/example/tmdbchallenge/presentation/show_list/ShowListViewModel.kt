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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowListViewModel @Inject constructor(
    private val useCases: ShowListUseCases
) : ViewModel() {

    var state by mutableStateOf(ShowListState())
        private set

    private var searchJob: Job? = null

    init {
        getShows(page = 1)
    }

    fun onEvent(event: ShowListEvent) {
        when (event) {
            is ShowListEvent.OnFirstVisibleListIndex -> {
                val showsSize = state.shows.size
                if (showsSize > 2632) return
                if (!state.isRefreshing && event.index == showsSize - 18) {
                    state = state.copy(isRefreshing = true)
                    getShows(page = (showsSize / 20) + 1)
                }
            }
            is ShowListEvent.QueryChanged -> {
                state = state.copy(showNameQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getShows(1)
                }
            }
            is ShowListEvent.FilterChanged -> {
                state = state.copy(showFilter = event.filter)
                getShows(1)
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
                    state = state.copy(isRefreshing = false)
                }
            }
            is Resource.Error -> {
                // TODO: Propagate error to view
                state = state.copy(isRefreshing = false)
            }
            is Resource.Loading -> {
                state = state.copy(isLoading = result.isLoading)
            }
        }
    }
}