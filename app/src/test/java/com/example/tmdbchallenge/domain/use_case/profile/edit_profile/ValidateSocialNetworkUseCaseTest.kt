package com.example.tmdbchallenge.domain.use_case.profile.edit_profile

import org.junit.Before
import org.junit.Test

class ValidateSocialNetworkUseCaseTest {

    private lateinit var validateSocialNetwork: ValidateSocialNetworkUseCase

    @Before
    fun setUp() {
        validateSocialNetwork = ValidateSocialNetworkUseCase()
    }

    @Test
    fun `Social Network blank, returns error`() {
        val result = validateSocialNetwork("")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Social Network with spaces, returns error`() {
        val result = validateSocialNetwork("a social network with spaces")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Social Network with more than 16 characters, returns error`() {
        val result = validateSocialNetwork("12345678901234567")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Correct Social Network, returns success`() {
        val result = validateSocialNetwork("goodNetwork")
        assert(result.isSuccessful)
    }
}