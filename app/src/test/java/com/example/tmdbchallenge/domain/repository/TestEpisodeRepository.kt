package com.example.tmdbchallenge.domain.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Episode

class TestEpisodeRepository : EpisodeRepository {

    override suspend fun getEpisodes(
        showId: Int,
        seasonNumber: Int,
        seasonId: Int
    ): Resource<List<Episode>> {
        return Resource.Success(
            listOf(
                Episode(
                    id = 1,
                    episodeNumber = 3,
                    seasonId = 1,
                    name = "Name",
                    summary = "Summary",
                    stillPath = "path",
                    runtime = 3,
                )
            )
        )
    }
}