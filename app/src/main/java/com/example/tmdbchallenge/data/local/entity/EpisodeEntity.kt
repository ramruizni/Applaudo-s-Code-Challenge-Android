package com.example.tmdbchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var episodeNumber: Int,
    var seasonId: Int,
    var name: String,
    var summary: String,
    var stillPath: String,
    var runtime: Int,
)