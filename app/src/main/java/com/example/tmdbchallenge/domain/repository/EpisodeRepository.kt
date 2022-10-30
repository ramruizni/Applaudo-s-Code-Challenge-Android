package com.example.tmdbchallenge.domain.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Episode

interface EpisodeRepository {

    suspend fun getEpisodes(
        showId: Int,
        seasonNumber: Int,
        seasonId: Int,
    ): Resource<List<Episode>>
}