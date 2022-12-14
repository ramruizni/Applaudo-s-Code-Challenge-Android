package com.example.tmdbchallenge.testability

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestDispatchers: DispatcherProvider {
    private val testDispatcher = TestCoroutineDispatcher()
    override val main: CoroutineDispatcher
        get() = testDispatcher
    override val io: CoroutineDispatcher
        get() = testDispatcher
    override val default: CoroutineDispatcher
        get() = testDispatcher
}