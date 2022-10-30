package com.example.tmdbchallenge.data.remote.tmdb.dto

data class ShowDto(
    var id: Int,
    var name: String,
    var backdrop_path: String,
    var poster_path: String,
    var overview: String,
    var vote_average: Double,
    var popularity: Double,
)