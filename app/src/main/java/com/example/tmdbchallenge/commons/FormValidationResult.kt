package com.example.tmdbchallenge.commons

data class FormValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String? = null
)
