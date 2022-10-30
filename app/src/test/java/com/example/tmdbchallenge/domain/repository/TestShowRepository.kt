package com.example.tmdbchallenge.domain.repository

import com.example.tmdbchallenge.commons.Resource
import com.example.tmdbchallenge.domain.ShowFilter
import com.example.tmdbchallenge.domain.model.Show

class TestShowRepository : ShowRepository {

    private val fakeShow = Show(
        id = 1,
        name = "Ozark",
        thumbnailUrl = "thumbnail/url",
        posterUrl = "poster/url",
        summary = "A cool show",
        rating = 10.0,
        popularity = 9000.0,
    )

    override suspend fun getShowsByFilter(showFilter: ShowFilter, page: Int): Resource<List<Show>> {
        return Resource.Success(listOf(fakeShow, fakeShow))
    }

    override suspend fun getShowsByName(name: String, page: Int): Resource<List<Show>> {
        return Resource.Success(listOf(fakeShow, fakeShow))
    }
}