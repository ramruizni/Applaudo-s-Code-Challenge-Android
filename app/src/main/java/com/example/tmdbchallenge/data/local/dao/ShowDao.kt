package com.example.tmdbchallenge.data.local.dao

import androidx.room.*
import com.example.tmdbchallenge.data.local.entity.ShowEntity

@Dao
interface ShowDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<ShowEntity>)

    @Query("SELECT * FROM showentity WHERE id=:showId")
    suspend fun findById(showId: Int) : List<ShowEntity>

    @Query("SELECT * FROM showentity ORDER BY rating ASC")
    suspend fun findAllSortedByRating(): List<ShowEntity>

    @Query("SELECT * FROM showentity ORDER BY popularity ASC")
    suspend fun findAllSortedByPopularity(): List<ShowEntity>

    @Query("SELECT * FROM showentity WHERE isOnTv")
    suspend fun findAllOnTv(): List<ShowEntity>

    @Query("SELECT * FROM showentity WHERE isAiringToday")
    suspend fun findAllAiringToday(): List<ShowEntity>

    @Query("SELECT * FROM showentity WHERE isFavorite")
    suspend fun findAllFavorites(): List<ShowEntity>

    @Update
    suspend fun update(showEntity: ShowEntity)
}