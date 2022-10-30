package com.example.tmdbchallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episode(
    var id: Int,
    var episodeNumber: Int,
    var seasonId: Int,
    var name: String,
    var summary: String,
    var stillPath: String,
    var runtime: Int,
) : Parcelable