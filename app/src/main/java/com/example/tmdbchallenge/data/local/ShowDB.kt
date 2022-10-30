package com.example.tmdbchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbchallenge.data.local.dao.EpisodeDao
import com.example.tmdbchallenge.data.local.dao.SeasonDao
import com.example.tmdbchallenge.data.local.dao.ShowDao
import com.example.tmdbchallenge.data.local.entity.EpisodeEntity
import com.example.tmdbchallenge.data.local.entity.SeasonEntity
import com.example.tmdbchallenge.data.local.entity.ShowEntity

@Database(
    entities = [ShowEntity::class, SeasonEntity::class, EpisodeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShowDB : RoomDatabase() {
    abstract fun getShowDao(): ShowDao
    abstract fun getSeasonDao(): SeasonDao
    abstract fun getEpisodeDao(): EpisodeDao
}