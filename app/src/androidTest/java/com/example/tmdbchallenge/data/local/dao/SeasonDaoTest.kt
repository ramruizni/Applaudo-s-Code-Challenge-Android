package com.example.tmdbchallenge.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.tmdbchallenge.data.local.ShowDB
import com.example.tmdbchallenge.data.mapper.toEntity
import com.example.tmdbchallenge.domain.model.Season
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class SeasonDaoTest {
    private lateinit var db: ShowDB
    private lateinit var dao: SeasonDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShowDB::class.java
        ).allowMainThreadQueries().build()
        dao = db.getSeasonDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun filtersEntitiesByShowId() = runTest {
        val entity1 = Season(
            id = 1,
            seasonNumber = 1,
            showId = 1,
            name = "Season 1",
            originalName = "Season 1",
            summary = "Summary",
            posterUrl = "posterUrl",
            episodeCount = 2,
        ).toEntity()

        val entity2 = Season(
            id = 2,
            seasonNumber = 2,
            showId = 1,
            name = "Season 2",
            originalName = "Season 2",
            summary = "Summary",
            posterUrl = "posterUrl",
            episodeCount = 2,
        ).toEntity()

        val entity3 = Season(
            id = 7,
            seasonNumber = 7,
            showId = 5,
            name = "Season 7",
            originalName = "Season 7",
            summary = "Summary",
            posterUrl = "posterUrl",
            episodeCount = 2,
        ).toEntity()

        dao.insertAll(listOf(entity1, entity2, entity3))

        var filtered = dao.findByShowId(showId = 1)
        assert(filtered == listOf(entity1, entity2))

        filtered = dao.findByShowId(showId = 5)
        assert(filtered == listOf(entity3))

        filtered = dao.findByShowId(showId = 7)
        assert(filtered.isEmpty())
    }
}