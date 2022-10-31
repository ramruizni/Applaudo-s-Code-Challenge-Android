package com.example.tmdbchallenge.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.tmdbchallenge.data.local.ShowDB
import com.example.tmdbchallenge.data.mapper.toEntity
import com.example.tmdbchallenge.domain.model.Show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShowDaoTest {
    private lateinit var db: ShowDB
    private lateinit var dao: ShowDao

    private val entity1 = Show(
        id = 1,
        name = "Ozark",
        originalName = "Ozark",
        thumbnailUrl = "thumbnail/url",
        posterUrl = "poster/url",
        summary = "A good show",
        rating = 9.0,
        popularity = 3000.0,
        isOnTv = true,
        isAiringToday = true,
        isFavorite = true
    ).toEntity()

    private val entity2 = Show(
        id = 2,
        name = "Breaking Bad",
        originalName = "Breaking Bad",
        thumbnailUrl = "thumbnail/url",
        posterUrl = "poster/url",
        summary = "Another good show",
        rating = 10.0,
        popularity = 7000.0,
        isOnTv = false,
        isAiringToday = false,
        isFavorite = false
    ).toEntity()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShowDB::class.java
        ).allowMainThreadQueries().build()
        dao = db.getShowDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertsMultipleEntitiesSuccessfully() = runTest {
        val entitiesToInsert = listOf(entity1, entity2)
        dao.insertAll(entitiesToInsert)
        val entitiesInserted = dao.findAllSortedByRating()
        assert(entitiesInserted.containsAll(entitiesToInsert))
    }

    @Test
    fun sortsEntitiesByRatingSuccessfully() = runTest {
        val entitiesToInsert = listOf(entity1, entity2)
        dao.insertAll(entitiesToInsert)
        val entitiesInserted = dao.findAllSortedByRating()
        val entitiesSorted = entitiesToInsert.sortedBy { it.rating }
        assert(entitiesInserted == entitiesSorted)
    }

    @Test
    fun sortsEntitiesByPopularitySuccessfully() = runTest {
        val entitiesToInsert = listOf(entity1, entity2)
        dao.insertAll(entitiesToInsert)
        val entitiesInserted = dao.findAllSortedByPopularity()
        val entitiesSorted = entitiesToInsert.sortedBy { it.popularity }
        assert(entitiesInserted == entitiesSorted)
    }

    @Test
    fun filtersEntitiesIfOnTvSuccessfully() = runTest {
        val entitiesToInsert = listOf(entity1, entity2)
        dao.insertAll(entitiesToInsert)
        val entitiesInserted = dao.findAllOnTv()
        val entitiesFiltered = entitiesToInsert.filter { it.isOnTv }
        assert(entitiesInserted == entitiesFiltered)
    }

    @Test
    fun filtersEntitiesIfAiringTodaySuccessfully() = runTest {
        val entitiesToInsert = listOf(entity1, entity2)
        dao.insertAll(entitiesToInsert)
        val entitiesInserted = dao.findAllAiringToday()
        val entitiesFiltered = entitiesToInsert.filter { it.isAiringToday }
        assert(entitiesInserted == entitiesFiltered)
    }

    @Test
    fun filtersEntitiesIfFavoritesSuccessfully() = runTest {
        val entitiesToInsert = listOf(entity1, entity2)
        dao.insertAll(entitiesToInsert)
        val entitiesInserted = dao.findAllAiringToday()
        val entitiesFiltered = entitiesToInsert.filter { it.isFavorite }
        assert(entitiesInserted == entitiesFiltered)
    }

    @Test
    fun updatesEntitySuccessfully() = runTest {
        dao.insertAll(listOf(entity1))
        val entityUpdated = entity1.copy(name = "The Office")
        dao.update(entityUpdated)
        val entitiesInserted = dao.findAllSortedByRating()
        assert(entitiesInserted == listOf(entityUpdated))
    }
}