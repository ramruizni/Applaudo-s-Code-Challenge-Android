package com.example.tmdbchallenge.domain.use_case.profile.edit_profile

import com.example.tmdbchallenge.commons.FormValidationResult


class ValidateNameUseCase {

    operator fun invoke(name: String): FormValidationResult {
        if (name.isBlank()) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The name can't be blank"
            )
        }
        if (name.length > 30)
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The name must be less than 30 characters"
            )
        return FormValidationResult(
            isSuccessful = true
        )
    }

}