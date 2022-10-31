package com.example.tmdbchallenge.domain.use_case.profile.edit_profile

data class EditProfileUseCases (
    val validateName: ValidateNameUseCase,
    val validateSocialNetwork: ValidateSocialNetworkUseCase,
)