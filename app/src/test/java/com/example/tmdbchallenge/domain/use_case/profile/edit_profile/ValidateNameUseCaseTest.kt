package com.example.tmdbchallenge.domain.use_case.profile.edit_profile

import org.junit.Before
import org.junit.Test

class ValidateNameUseCaseTest {

    private lateinit var validateName: ValidateNameUseCase

    @Before
    fun setUp() {
        validateName = ValidateNameUseCase()
    }

    @Test
    fun `Name blank, returns error`() {
        val result = validateName("")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Name with more than 30 characters, returns error`() {
        val result = validateName("1234567890123456789012345678901")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Correct Name, returns success`() {
        val result = validateName("Mike Phillip")
        assert(result.isSuccessful)
    }
}