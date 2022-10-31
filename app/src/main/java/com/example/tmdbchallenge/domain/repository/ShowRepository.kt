package com.example.tmdbchallenge.domain.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.ShowFilter
import com.example.tmdbchallenge.domain.model.Show

interface ShowRepository {

    suspend fun getShowsByFilter(
        showFilter: ShowFilter,
        page: Int
    ): Resource<List<Show>>

    suspend fun getShowsByName(
        name: String,
        page: Int
    ): Resource<List<Show>>

    suspend fun findShowById(
        showId: Int
    ): Resource<Show>

    suspend fun updateShow(
        show: Show
    ): Resource<Show>

    suspend fun getFavoriteShows(): Resource<List<Show>>
}