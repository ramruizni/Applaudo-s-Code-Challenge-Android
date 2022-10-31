package com.example.tmdbchallenge.data.remote.tmdb.api

import com.example.tmdbchallenge.commons.Constants.API_KEY
import com.example.tmdbchallenge.data.remote.tmdb.dto.season.SeasonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SeasonApi {

    @GET("tv/{showId}?api_key=$API_KEY")
    suspend fun getSeasons(
        @Path("showId") showId: Int
    ): SeasonListResponseDto
}