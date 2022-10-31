package com.example.tmdbchallenge.presentation.show_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.domain.use_case.show_details.ShowDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val useCases: ShowDetailsUseCases,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(ShowDetailsState())
        private set

    init {
        state = state.copy(show = stateHandle.get<Show>("show"))
        getSeasons()
    }

    fun onEvent(event: ShowDetailsEvent) {
        when (event) {
            is ShowDetailsEvent.ToggleFavorite -> {
                state.show?.let { show ->
                    viewModelScope.launch {
                        useCases
                            .toggleShowAsFavorite(show)
                            .collect { result ->
                                when (result) {
                                    is Resource.Success -> {
                                        result.data?.let { showToggled ->
                                            state = state.copy(show = showToggled)
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
                }
            }
        }
    }

    private fun getSeasons() {
        viewModelScope.launch {
            state.show?.let { show ->
                useCases.getSeasons(show.id)
                    .collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let { seasons ->
                                    state = state.copy(seasons = seasons)
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
        }
    }
}