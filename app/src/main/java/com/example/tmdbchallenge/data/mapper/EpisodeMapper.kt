package com.example.tmdbchallenge.data.mapper

import com.example.tmdbchallenge.data.local.entity.EpisodeEntity
import com.example.tmdbchallenge.data.remote.tmdb.dto.episode.EpisodeDto
import com.example.tmdbchallenge.domain.model.Episode


fun Episode.toEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        episodeNumber = episodeNumber,
        seasonId = seasonId,
        name = name,
        summary = summary,
        stillPath = stillPath,
        runtime = runtime,
    )
}

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = id,
        episodeNumber = episodeNumber,
        seasonId = seasonId,
        name = name,
        summary = summary,
        stillPath = stillPath,
        runtime = runtime,
    )
}

fun EpisodeDto.toEpisode(seasonId: Int): Episode {
    return Episode(
        id = id,
        episodeNumber = episode_number,
        seasonId = seasonId,
        name = name,
        summary = overview ?: "",
        stillPath = still_path ?: "",
        runtime = runtime ?: 38,
    )
}