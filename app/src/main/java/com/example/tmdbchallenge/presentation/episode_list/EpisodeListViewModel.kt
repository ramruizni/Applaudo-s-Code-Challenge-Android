package com.example.tmdbchallenge.presentation.episode_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.domain.use_case.episode_list.EpisodeListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    private val useCases: EpisodeListUseCases,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(EpisodeListState())
        private set

    init {
        state = state.copy(show = stateHandle.get<Show>("show"))
        state = state.copy(season = stateHandle.get<Season>("season"))
        getSeasons()
    }

    private fun getSeasons() {
        viewModelScope.launch {
            state.season?.let { season ->
                state.show?.let { show ->
                    useCases.getEpisodes(
                        showId = show.id,
                        seasonNumber = season.seasonNumber,
                        seasonId = season.id
                    )
                        .collect { result ->
                            when (result) {
                                is Resource.Success -> {
                                    result.data?.let { seasons ->
                                        state = state.copy(episodes = seasons)
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