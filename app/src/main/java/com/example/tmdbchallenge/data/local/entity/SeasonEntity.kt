package com.example.tmdbchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SeasonEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var seasonNumber: Int,
    var showId: Int,
    var name: String,
    var originalName: String,
    var summary: String,
    var posterUrl: String,
    var episodeCount: Int,
)