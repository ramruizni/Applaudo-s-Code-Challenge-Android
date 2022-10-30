package com.example.tmdbchallenge.domain.use_case.show_list

data class ShowListUseCases(
    val getShowsByFilter: GetShowsByFilterUseCase,
    val getShowsByName: GetShowsByNameUseCase,
)