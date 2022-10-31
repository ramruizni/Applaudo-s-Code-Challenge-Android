package com.example.tmdbchallenge.domain.use_case.login

import com.example.tmdbchallenge.commons.FormValidationResult

class ValidatePasswordUseCase {

    operator fun invoke(password: String): FormValidationResult {
        if (password.length < 8) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The password must have at least 8 characters"
            )
        }
        val containsLetterAndDigit = password.any { it.isLetter() } && password.any { it.isDigit() }
        if (!containsLetterAndDigit) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The password needs to contain at least one letter and one digit"
            )
        }
        return FormValidationResult(
            isSuccessful = true
        )
    }

}