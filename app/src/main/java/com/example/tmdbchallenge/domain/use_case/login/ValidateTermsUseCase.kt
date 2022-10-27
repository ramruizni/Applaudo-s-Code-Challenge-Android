package com.example.tmdbchallenge.domain.use_case.login

class ValidateTermsUseCase {

    operator fun invoke(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

}