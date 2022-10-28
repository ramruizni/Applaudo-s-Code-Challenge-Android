package com.example.tmdbchallenge.data.remote.tmdb.dto

data class ShowListResponseDto (
    val page: Int,
    val results: List<ShowDto>,
    val total_pages: Int,
    val total_results: Int,
)