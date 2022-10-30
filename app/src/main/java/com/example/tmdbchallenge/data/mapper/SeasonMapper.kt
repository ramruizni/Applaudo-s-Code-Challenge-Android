package com.example.tmdbchallenge.data.mapper

import com.example.tmdbchallenge.data.local.entity.SeasonEntity
import com.example.tmdbchallenge.data.remote.tmdb.dto.season.SeasonDto
import com.example.tmdbchallenge.domain.model.Season

fun Season.toEntity(): SeasonEntity {
    return SeasonEntity(
        id = id,
        seasonNumber = seasonNumber,
        showId = showId,
        name = name,
        originalName = originalName,
        summary = summary,
        posterUrl = posterUrl,
        episodeCount = episodeCount,
    )
}

fun SeasonEntity.toSeason(): Season {
    return Season(
        id = id,
        seasonNumber = seasonNumber,
        showId = showId,
        name = name,
        originalName = originalName,
        summary = summary,
        posterUrl = posterUrl,
        episodeCount = episodeCount,
    )
}

fun SeasonDto.toSeason(showId: Int): Season {
    return Season(
        id = id,
        seasonNumber = season_number,
        showId = showId,
        name = name,
        originalName = original_name ?: "",
        summary = overview ?: "",
        posterUrl = poster_path ?: "",
        episodeCount = episode_count ?: 7,
    )
}