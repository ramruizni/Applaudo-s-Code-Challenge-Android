package com.example.tmdbchallenge.domain.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Season

interface SeasonRepository {

    suspend fun getSeasons(
        showId: Int,
    ): Resource<List<Season>>
}