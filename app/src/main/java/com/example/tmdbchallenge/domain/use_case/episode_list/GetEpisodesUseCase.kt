package com.example.tmdbchallenge.domain.use_case.episode_list

import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.commons.UiText
import com.example.tmdbchallenge.domain.model.Episode
import com.example.tmdbchallenge.domain.repository.EpisodeRepository
import com.example.tmdbchallenge.testability.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val episodeRepository: EpisodeRepository
) {
    suspend operator fun invoke(
        showId: Int,
        seasonNumber: Int,
        seasonId: Int,
    ): Flow<Resource<List<Episode>>> {
        return flow {
            emit(Resource.Loading(true))

            val filteredResult = episodeRepository.getEpisodes(
                showId = showId,
                seasonNumber = seasonNumber,
                seasonId = seasonId
            )

            when (filteredResult) {
                is Resource.Success -> {
                    emit(filteredResult)
                }
                is Resource.Error -> {
                    emit(Resource.Error(UiText.StringResource(R.string.error_unknown)))
                }
                else -> Unit
            }

            emit(Resource.Loading(false))
        }.catch {
            emit(Resource.Error(UiText.StringResource(R.string.error_unknown)))
        }.flowOn(dispatchers.io)
    }
}

