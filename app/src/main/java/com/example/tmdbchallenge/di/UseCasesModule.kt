package com.example.tmdbchallenge.di

import com.example.tmdbchallenge.domain.repository.ShowRepository
import com.example.tmdbchallenge.domain.use_case.login.LoginUseCases
import com.example.tmdbchallenge.domain.use_case.login.ValidateEmailUseCase
import com.example.tmdbchallenge.domain.use_case.login.ValidatePasswordUseCase
import com.example.tmdbchallenge.domain.use_case.login.ValidateTermsUseCase
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
}