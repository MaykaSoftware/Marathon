package com.sportunity.marathon.di

import com.sportunity.marathon.data.local.dao.MarathonEventDao
import com.sportunity.marathon.data.local.database.MarathonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Singleton
    @Provides
    fun provideArtObjectDao(marathonDatabase: MarathonDatabase): MarathonEventDao =
        marathonDatabase.getMarathonEvents()
}