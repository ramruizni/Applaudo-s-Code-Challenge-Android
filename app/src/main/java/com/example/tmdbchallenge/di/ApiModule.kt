package com.example.tmdbchallenge.di

import com.example.tmdbchallenge.commons.Constants.API_URL
import com.example.tmdbchallenge.data.remote.tmdb.api.EpisodeApi
import com.example.tmdbchallenge.data.remote.tmdb.api.SeasonApi
import com.example.tmdbchallenge.data.remote.tmdb.api.ShowApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideShowApi(retrofit: Retrofit): ShowApi =
        retrofit.create(ShowApi::class.java)

    @Provides
    @Singleton
    fun provideSeasonApi(retrofit: Retrofit): SeasonApi =
        retrofit.create(SeasonApi::class.java)

    @Provides
    @Singleton
    fun provideEpisodeApi(retrofit: Retrofit): EpisodeApi =
        retrofit.create(EpisodeApi::class.java)
}