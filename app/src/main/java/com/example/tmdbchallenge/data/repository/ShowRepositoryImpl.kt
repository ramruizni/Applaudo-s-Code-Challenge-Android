package com.example.tmdbchallenge.data.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.data.local.dao.ShowDao
import com.example.tmdbchallenge.data.mapper.toEntity
import com.example.tmdbchallenge.data.mapper.toShow
import com.example.tmdbchallenge.data.remote.tmdb.api.ShowApi
import com.example.tmdbchallenge.domain.ShowFilter
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.domain.repository.ShowRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowRepositoryImpl @Inject constructor(
    private val api: ShowApi,
    private val dao: ShowDao,
) : ShowRepository {

    override suspend fun getShowsByFilter(
        showFilter: ShowFilter,
        page: Int
    ): Resource<List<Show>> {
        val responseFiltered = when (showFilter) {
            ShowFilter.TOP_RATED -> api.getTopRatedShows(page)
            ShowFilter.POPULAR -> api.getPopularShows(page)
            ShowFilter.ON_TV -> api.getOnTvShows(page)
            ShowFilter.AIRING_TODAY -> api.getAiringTodayShows(page)
        }

        val listFromRemote = responseFiltered.results.map { it.toShow() }
        dao.insertAll(listFromRemote.map { it.toEntity() })
        return Resource.Success(listFromRemote)
    }

    override suspend fun getShowsByName(name: String, page: Int): Resource<List<Show>> {
        val responseFiltered = api.getShowsByName(name, page)

        val listFromRemote = responseFiltered.results.map { it.toShow() }
        dao.insertAll(listFromRemote.map { it.toEntity() })
        return Resource.Success(listFromRemote)
    }

}