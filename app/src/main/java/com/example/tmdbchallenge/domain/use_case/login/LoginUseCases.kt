package com.example.tmdbchallenge.domain.use_case.login

data class LoginUseCases(
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val validateTerms: ValidateTermsUseCase
)
