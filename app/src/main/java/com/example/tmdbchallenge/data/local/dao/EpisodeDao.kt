package com.example.tmdbchallenge.data.local.dao

import androidx.room.*
import com.example.tmdbchallenge.data.local.entity.EpisodeEntity

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<EpisodeEntity>)

    @Query("SELECT * FROM episodeentity WHERE seasonId=:seasonId")
    suspend fun findBySeasonId(seasonId: Int): List<EpisodeEntity>
}