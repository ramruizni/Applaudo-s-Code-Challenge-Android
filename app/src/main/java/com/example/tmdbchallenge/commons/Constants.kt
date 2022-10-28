package com.example.tmdbchallenge.commons

import com.example.tmdbchallenge.BuildConfig

object Constants {
    const val API_URL = BuildConfig.API_URL
    const val API_KEY = BuildConfig.API_KEY
    const val API_IMAGE_URL = BuildConfig.API_IMAGE_URL

    const val DB_NAME = "db_name"

    const val SHARED_PREF_NAME = "prefs_name"
    const val SHARED_PREF_IS_LOGGED_IN = "prefs_name_is_logged_in"
}