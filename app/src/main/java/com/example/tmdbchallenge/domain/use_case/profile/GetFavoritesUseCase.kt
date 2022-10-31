package com.example.tmdbchallenge.domain.use_case.profile

import com.example.tmdbchallenge.R
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.commons.UiText
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.domain.repository.ShowRepository
import com.example.tmdbchallenge.testability.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val showRepository: ShowRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<Show>>> {
        return flow {
            emit(Resource.Loading(true))

            val favoritesResult = showRepository.getFavoriteShows()

            when (favoritesResult) {
                is Resource.Success -> {
                    emit(favoritesResult)
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