package com.example.tmdbchallenge.domain.use_case.show_list

import app.cash.turbine.test
import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.repository.ShowRepository
import com.example.tmdbchallenge.domain.repository.TestShowRepository
import com.example.tmdbchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetShowsByFilterUseCaseTest {

    private lateinit var getShowsByFilterUseCase: GetShowsByFilterUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testShowRepository: ShowRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testShowRepository = TestShowRepository()
        getShowsByFilterUseCase = GetShowsByFilterUseCase(
            dispatchers = testDispatchers,
            showRepository = testShowRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        getShowsByFilterUseCase().test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}