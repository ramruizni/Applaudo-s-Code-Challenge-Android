package com.example.tmdbchallenge.data.mapper

import com.example.tmdbchallenge.data.local.entity.ShowEntity
import com.example.tmdbchallenge.data.remote.tmdb.dto.ShowDto
import com.example.tmdbchallenge.domain.model.Show

fun Show.toEntity(): ShowEntity {
    return ShowEntity(
        id= id,
        name = name,
        thumbnailUrl = thumbnailUrl,
        posterUrl = posterUrl,
        summary = summary,
        rating = rating,
        popularity = popularity,
        isOnTv = isOnTv,
        isAiringToday = isAiringToday,
        isFavorite = isFavorite,
    )
}

fun ShowEntity.toShow(): Show {
    return Show(
        id= id,
        name = name,
        thumbnailUrl = thumbnailUrl,
        posterUrl = posterUrl,
        summary = summary,
        rating = rating,
        popularity = popularity,
        isOnTv = isOnTv,
        isAiringToday = isAiringToday,
        isFavorite = isFavorite,
    )
}

fun ShowDto.toShow(): Show {
    return Show(
        id = id,
        name = name,
        thumbnailUrl = backdrop_path,
        posterUrl = poster_path,
        summary = overview,
        rating = vote_average,
        popularity = popularity,
    )
}