package com.example.tmdbchallenge.domain.use_case.login

import com.example.tmdbchallenge.commons.FormValidationResult
import com.example.tmdbchallenge.commons.Matchers


class ValidateEmailUseCase {

    operator fun invoke(email: String): FormValidationResult {
        if (email.isBlank()) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The email can't be blank"
            )
        }

        if (!Matchers.isValidEmail(email)) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return FormValidationResult(
            isSuccessful = true
        )
    }

}