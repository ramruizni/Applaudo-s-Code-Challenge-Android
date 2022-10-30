package com.example.tmdbchallenge.data.remote.tmdb.api

import com.example.tmdbchallenge.commons.Constants.API_KEY
import com.example.tmdbchallenge.data.remote.tmdb.dto.show.ShowListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowApi {

    @GET("tv/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedShows(
        @Query("page") page: Int
    ): ShowListResponseDto

    @GET("tv/popular?api_key=$API_KEY")
    suspend fun getPopularShows(
        @Query("page") page: Int
    ): ShowListResponseDto

    @GET("tv/on_the_air?api_key=$API_KEY")
    suspend fun getOnTvShows(
        @Query("page") page: Int
    ): ShowListResponseDto

    @GET("tv/airing_today?api_key=$API_KEY")
    suspend fun getAiringTodayShows(
        @Query("page") page: Int
    ): ShowListResponseDto

    @GET("search/tv?api_key=$API_KEY")
    suspend fun getShowsByName(
        @Query("query") name: String,
        @Query("page") page: Int
    ): ShowListResponseDto
}