package com.example.tmdbchallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    var id: Int,
    var seasonNumber: Int,
    var showId: Int,
    var name: String,
    var originalName: String,
    var summary: String,
    var posterUrl: String,
    var episodeCount: Int,
) : Parcelable