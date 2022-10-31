package com.example.tmdbchallenge.domain.use_case.show_details

import app.cash.turbine.test
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.model.Show
import com.example.tmdbchallenge.domain.repository.ShowRepository
import com.example.tmdbchallenge.domain.repository.TestShowRepository
import com.example.tmdbchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ToggleShowAsFavoriteUseCaseTest {
    private lateinit var toggleShowAsFavorite: ToggleShowAsFavoriteUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testShowRepository: ShowRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testShowRepository = TestShowRepository()
        toggleShowAsFavorite = ToggleShowAsFavoriteUseCase(
            dispatchers = testDispatchers,
            showRepository = testShowRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        val fakeShow = Show(
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
        )
        toggleShowAsFavorite(show = fakeShow).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}