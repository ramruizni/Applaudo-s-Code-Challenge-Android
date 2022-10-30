package com.example.tmdbchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbchallenge.data.local.dao.ShowDao
import com.example.tmdbchallenge.data.local.entity.ShowEntity

@Database(
    entities = [ShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShowDB: RoomDatabase() {
    abstract fun getShowDao(): ShowDao
}