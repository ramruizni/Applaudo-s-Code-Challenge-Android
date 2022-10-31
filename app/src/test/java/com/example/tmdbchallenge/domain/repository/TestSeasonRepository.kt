package com.example.tmdbchallenge.domain.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Season

class TestSeasonRepository : SeasonRepository {

    override suspend fun getSeasons(showId: Int): Resource<List<Season>> {
        return Resource.Success(listOf(
            Season(
                id = 1,
                seasonNumber = 1,
                showId = 1,
                name = "Season 1",
                originalName = "Season 1",
                summary = "Summary",
                posterUrl = "posterUrl",
                episodeCount = 2,
            )
        ))
    }
}