package com.example.tmdbchallenge.domain.use_case.episode_list

import app.cash.turbine.test
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.repository.EpisodeRepository
import com.example.tmdbchallenge.domain.repository.TestEpisodeRepository
import com.example.tmdbchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetEpisodesUseCaseTest {
    private lateinit var getEpisodes: GetEpisodesUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testEpisodeRepository: EpisodeRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testEpisodeRepository = TestEpisodeRepository()
        getEpisodes = GetEpisodesUseCase(
            dispatchers = testDispatchers,
            episodeRepository = testEpisodeRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        getEpisodes(
            showId = 1,
            seasonNumber = 1,
            seasonId = 1,
        ).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}