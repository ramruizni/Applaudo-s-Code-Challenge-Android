package com.example.tmdbchallenge.domain.use_case.login

import com.example.tmdbchallenge.commons.FormValidationResult

class ValidateTermsUseCase {

    operator fun invoke(acceptedTerms: Boolean): FormValidationResult {
        if (!acceptedTerms) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return FormValidationResult(
            isSuccessful = true
        )
    }

}