package com.example.tmdbchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.tmdbchallenge.commons.Constants.DB_NAME
import com.example.tmdbchallenge.data.local.ShowDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ShowDB::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideShowDao(db: ShowDB) = db.getShowDao()

    @Provides
    @Singleton
    fun provideSeasonDao(db: ShowDB) = db.getSeasonDao()

    @Provides
    @Singleton
    fun provideEpisodeDao(db: ShowDB) = db.getEpisodeDao()

}