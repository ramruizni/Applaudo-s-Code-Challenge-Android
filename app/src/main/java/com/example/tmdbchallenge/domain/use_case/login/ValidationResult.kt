package com.example.tmdbchallenge.domain.use_case.login

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String? = null
)
