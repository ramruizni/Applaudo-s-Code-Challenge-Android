package com.example.tmdbchallenge.di

import com.example.tmdbchallenge.domain.repository.EpisodeRepository
import com.example.tmdbchallenge.domain.repository.SeasonRepository
import com.example.tmdbchallenge.domain.repository.ShowRepository
import com.example.tmdbchallenge.domain.use_case.episode_list.EpisodeListUseCases
import com.example.tmdbchallenge.domain.use_case.episode_list.GetEpisodesUseCase
import com.example.tmdbchallenge.domain.use_case.login.LoginUseCases
import com.example.tmdbchallenge.domain.use_case.login.ValidateEmailUseCase
import com.example.tmdbchallenge.domain.use_case.login.ValidatePasswordUseCase
import com.example.tmdbchallenge.domain.use_case.login.ValidateTermsUseCase
import com.example.tmdbchallenge.domain.use_case.show_details.GetSeasonsUseCase
import com.example.tmdbchallenge.domain.use_case.show_details.ShowDetailsUseCases
import com.example.tmdbchallenge.domain.use_case.show_list.GetShowsByFilterUseCase
import com.example.tmdbchallenge.domain.use_case.show_list.GetShowsByNameUseCase
import com.example.tmdbchallenge.domain.use_case.show_list.ShowListUseCases
import com.example.tmdbchallenge.testability.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideLoginUseCases(): LoginUseCases {
        return LoginUseCases(
            ValidateEmailUseCase(),
            ValidatePasswordUseCase(),
            ValidateTermsUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideShowListUseCases(
        dispatchers: DispatcherProvider,
        showRepository: ShowRepository,
    ): ShowListUseCases {
        return ShowListUseCases(
            GetShowsByFilterUseCase(dispatchers, showRepository),
            GetShowsByNameUseCase(dispatchers, showRepository),
        )
    }

    @Provides
    @Singleton
    fun provideShowDetailsUseCases(
        dispatchers: DispatcherProvider,
        seasonRepository: SeasonRepository,
    ): ShowDetailsUseCases {
        return ShowDetailsUseCases(
            GetSeasonsUseCase(dispatchers, seasonRepository)
        )
    }

    @Provides
    @Singleton
    fun provideEpisodeListUseCases(
        dispatchers: DispatcherProvider,
        episodeRepository: EpisodeRepository,
    ): EpisodeListUseCases {
        return EpisodeListUseCases(
            GetEpisodesUseCase(dispatchers, episodeRepository)
        )
    }
}