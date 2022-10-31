package com.example.tmdbchallenge.data.remote.tmdb.dto.season

data class SeasonDto(
    val id: Int,
    val season_number: Int,
    val name: String,
    val original_name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val episode_count: Int? = null,
)