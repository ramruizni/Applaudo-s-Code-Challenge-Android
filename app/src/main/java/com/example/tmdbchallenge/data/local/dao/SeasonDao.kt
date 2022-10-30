package com.example.tmdbchallenge.data.local.dao

import androidx.room.*
import com.example.tmdbchallenge.data.local.entity.SeasonEntity

@Dao
interface SeasonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<SeasonEntity>)

    @Query("SELECT * FROM seasonentity WHERE showId=:showId")
    suspend fun findByShowId(showId: Int): List<SeasonEntity>
}