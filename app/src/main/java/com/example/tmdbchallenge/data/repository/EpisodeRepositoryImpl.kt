package com.example.tmdbchallenge.data.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.data.local.dao.EpisodeDao
import com.example.tmdbchallenge.data.mapper.toEntity
import com.example.tmdbchallenge.data.mapper.toEpisode
import com.example.tmdbchallenge.data.remote.tmdb.api.EpisodeApi
import com.example.tmdbchallenge.domain.model.Episode
import com.example.tmdbchallenge.domain.repository.EpisodeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeRepositoryImpl @Inject constructor(
    private val api: EpisodeApi,
    private val dao: EpisodeDao,
) : EpisodeRepository {

    override suspend fun getEpisodes(
        showId: Int,
        seasonNumber: Int,
        seasonId: Int
    ): Resource<List<Episode>> {
        val listFromCache = dao.findBySeasonId(seasonId)

        if (listFromCache.isNotEmpty()) {
            return Resource.Success(listFromCache.map { it.toEpisode() })
        }

        val response = api.getEpisodes(showId, seasonNumber)

        val listFromRemote = response.episodes.map { it.toEpisode(seasonId) }
        dao.insertAll(listFromRemote.map { it.toEntity() })
        return Resource.Success(listFromRemote)
    }

}