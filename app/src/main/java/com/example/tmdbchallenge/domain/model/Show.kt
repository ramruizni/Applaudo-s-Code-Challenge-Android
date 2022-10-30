package com.example.tmdbchallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
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
) : Parcelable