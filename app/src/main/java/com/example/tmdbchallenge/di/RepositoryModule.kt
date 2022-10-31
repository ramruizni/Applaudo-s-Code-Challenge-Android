package com.example.tmdbchallenge.di

import com.example.tmdbchallenge.data.repository.EpisodeRepositoryImpl
import com.example.tmdbchallenge.data.repository.SeasonRepositoryImpl
import com.example.tmdbchallenge.data.repository.ShowRepositoryImpl
import com.example.tmdbchallenge.domain.repository.EpisodeRepository
import com.example.tmdbchallenge.domain.repository.SeasonRepository
import com.example.tmdbchallenge.domain.repository.ShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShowRepository(
        showRepositoryImpl: ShowRepositoryImpl
    ): ShowRepository

    @Binds
    @Singleton
    abstract fun bindSeasonRepository(
        seasonRepositoryImpl: SeasonRepositoryImpl
    ): SeasonRepository

    @Binds
    @Singleton
    abstract fun bindEpisodeRepository(
        episodeRepositoryImpl: EpisodeRepositoryImpl
    ): EpisodeRepository
}