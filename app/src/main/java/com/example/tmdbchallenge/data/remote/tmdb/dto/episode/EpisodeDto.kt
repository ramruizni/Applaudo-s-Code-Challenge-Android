package com.example.tmdbchallenge.data.remote.tmdb.dto.episode

data class EpisodeDto(
    val id: Int,
    val episode_number: Int,
    val name: String,
    val overview: String? = null,
    val still_path: String? = null,
    val runtime: Int? = null,
)