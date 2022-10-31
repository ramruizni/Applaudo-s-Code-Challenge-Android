package com.example.tmdbchallenge.data.remote.tmdb.api

import com.example.tmdbchallenge.commons.Constants.API_KEY
import com.example.tmdbchallenge.data.remote.tmdb.dto.episode.EpisodeListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("tv/{showId}/season/{seasonNumber}?api_key=$API_KEY")
    suspend fun getEpisodes(
        @Path("showId") showId: Int,
        @Path("seasonNumber") seasonNumber: Int
    ): EpisodeListResponseDto
}