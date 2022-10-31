package com.example.tmdbchallenge.domain.use_case.show_details

import app.cash.turbine.test
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.repository.SeasonRepository
import com.example.tmdbchallenge.domain.repository.TestSeasonRepository
import com.example.tmdbchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSeasonsUseCaseTest {
    private lateinit var getSeasons: GetSeasonsUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testSeasonRepository: SeasonRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testSeasonRepository = TestSeasonRepository()
        getSeasons = GetSeasonsUseCase(
            dispatchers = testDispatchers,
            seasonRepository = testSeasonRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        getSeasons(showId = 1).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}