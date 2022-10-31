package com.example.tmdbchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShowEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var originalName: String,
    var thumbnailUrl: String,
    var posterUrl: String,
    var summary: String,
    var rating: Double,
    var popularity: Double,
    var isOnTv: Boolean = false,
    var isAiringToday: Boolean = false,
    var isFavorite: Boolean = false,
)