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

    private var cachedList = mutableListOf<Show>()
    private var lastAppliedFilter = ShowFilter.TOP_RATED

    override suspend fun getShowsByFilter(
        showFilter: ShowFilter,
        page: Int
    ): Resource<List<Show>> {
        val queryPage = if (lastAppliedFilter == showFilter) page else 1

        val responseFiltered = when (showFilter) {
            ShowFilter.TOP_RATED -> api.getTopRatedShows(queryPage)
            ShowFilter.POPULAR -> api.getPopularShows(queryPage)
            ShowFilter.ON_TV -> api.getOnTvShows(queryPage)
            ShowFilter.AIRING_TODAY -> api.getAiringTodayShows(queryPage)
        }

        val listFromRemote = responseFiltered.results.map { it.toShow() }
        //dao.insertAll(listFromRemote.map { it.toEntity() })

        if (lastAppliedFilter == showFilter) {
            cachedList.addAll(listFromRemote)
        } else {
            cachedList = listFromRemote.toMutableList()
        }
        lastAppliedFilter = showFilter

        return Resource.Success(cachedList)
    }

    override suspend fun getShowsByName(name: String, page: Int): Resource<List<Show>> {
        val responseFiltered = api.getShowsByName(name, page)

        val listFromRemote = responseFiltered.results.map { it.toShow() }
        dao.insertAll(listFromRemote.map { it.toEntity() })
        return Resource.Success(listFromRemote)
    }

    override suspend fun findShowById(showId: Int): Resource<Show> {
        val showFromCache = dao.findById(showId)[0].toShow()
        return Resource.Success(showFromCache)
    }

    override suspend fun updateShow(show: Show): Resource<Show> {
        dao.update(show.toEntity())
        val showFromCache = dao.findById(show.id)[0].toShow()
        return Resource.Success(showFromCache)
    }

    override suspend fun getFavoriteShows(): Resource<List<Show>> {
        val listFromCache = dao.findAllFavorites()
        return Resource.Success(listFromCache.map { it.toShow() })
    }
}