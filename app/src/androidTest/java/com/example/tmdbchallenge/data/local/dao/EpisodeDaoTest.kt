package com.example.tmdbchallenge.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.tmdbchallenge.data.local.ShowDB
import com.example.tmdbchallenge.data.mapper.toEntity
import com.example.tmdbchallenge.domain.model.Episode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class EpisodeDaoTest {
    private lateinit var db: ShowDB
    private lateinit var dao: EpisodeDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShowDB::class.java
        ).allowMainThreadQueries().build()
        dao = db.getEpisodeDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun filtersEntitiesByShowId() = runTest {
        val entity1 = Episode(
            id = 1,
            episodeNumber = 3,
            seasonId = 1,
            name = "Name",
            summary = "Summary",
            stillPath = "path",
            runtime = 3,
        ).toEntity()

        val entity2 = Episode(
            id = 2,
            episodeNumber = 5,
            seasonId = 1,
            name = "Name",
            summary = "Summary",
            stillPath = "path",
            runtime = 3,
        ).toEntity()

        val entity3 = Episode(
            id = 3,
            episodeNumber = 6,
            seasonId = 70,
            name = "Name",
            summary = "Summary",
            stillPath = "path",
            runtime = 3,
        ).toEntity()

        dao.insertAll(listOf(entity1, entity2, entity3))

        var filtered = dao.findBySeasonId(seasonId = 1)
        assert(filtered == listOf(entity1, entity2))

        filtered = dao.findBySeasonId(seasonId = 70)
        assert(filtered == listOf(entity3))

        filtered = dao.findBySeasonId(seasonId = 2)
        assert(filtered.isEmpty())
    }
}