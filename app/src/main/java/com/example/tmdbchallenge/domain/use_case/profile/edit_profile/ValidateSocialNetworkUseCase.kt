package com.example.tmdbchallenge.domain.use_case.profile.edit_profile

import com.example.tmdbchallenge.commons.FormValidationResult


class ValidateSocialNetworkUseCase {

    operator fun invoke(socialNetwork: String): FormValidationResult {
        if (socialNetwork.isBlank()) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The social network can't be blank"
            )
        }
        if (socialNetwork.contains(' ')) {
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The social network cannot contain spaces"
            )
        }
        if (socialNetwork.length > 16)
            return FormValidationResult(
                isSuccessful = false,
                errorMessage = "The social network must be less than 16 characters"
            )
        return FormValidationResult(
            isSuccessful = true
        )
    }

}