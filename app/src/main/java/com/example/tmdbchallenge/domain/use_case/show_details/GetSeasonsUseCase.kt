package com.example.tmdbchallenge.domain.use_case.show_details

import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.commons.UiText
import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.domain.repository.SeasonRepository
import com.example.tmdbchallenge.testability.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSeasonsUseCase @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val seasonRepository: SeasonRepository,
) {
    suspend operator fun invoke(
        showId: Int
    ): Flow<Resource<List<Season>>> {
        return flow {
            emit(Resource.Loading(true))

            val filteredResult = seasonRepository.getSeasons(showId)

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

