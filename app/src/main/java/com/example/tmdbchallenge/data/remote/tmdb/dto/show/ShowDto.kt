package com.example.tmdbchallenge.data.remote.tmdb.dto.show

data class ShowDto(
    val id: Int,
    val name: String,
    val original_name: String? = null,
    val backdrop_path: String? = null,
    val poster_path: String? = null,
    val overview: String? = null,
    val vote_average: Double? = null,
    val popularity: Double? = null,
)