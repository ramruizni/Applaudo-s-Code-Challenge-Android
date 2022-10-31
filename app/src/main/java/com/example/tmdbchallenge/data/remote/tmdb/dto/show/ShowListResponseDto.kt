package com.example.tmdbchallenge.data.remote.tmdb.dto.show

data class ShowListResponseDto (
    val page: Int,
    val results: List<ShowDto>,
    val total_pages: Int,
    val total_results: Int,
)