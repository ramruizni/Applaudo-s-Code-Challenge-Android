package com.example.tmdbchallenge.data.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.data.local.dao.SeasonDao
import com.example.tmdbchallenge.data.mapper.toEntity
import com.example.tmdbchallenge.data.mapper.toSeason
import com.example.tmdbchallenge.data.remote.tmdb.api.SeasonApi
import com.example.tmdbchallenge.domain.model.Season
import com.example.tmdbchallenge.domain.repository.SeasonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeasonRepositoryImpl @Inject constructor(
    private val api: SeasonApi,
    private val dao: SeasonDao,
) : SeasonRepository {

    override suspend fun getSeasons(showId: Int): Resource<List<Season>> {
        val listFromCache = dao.findByShowId(showId)

        if (listFromCache.isNotEmpty()) {
            return Resource.Success(listFromCache.map { it.toSeason() })
        }

        val response = api.getSeasons(showId)

        val listFromRemote = response.seasons.map { it.toSeason(showId) }
        dao.insertAll(listFromRemote.map { it.toEntity() })
        return Resource.Success(listFromRemote)
    }

}