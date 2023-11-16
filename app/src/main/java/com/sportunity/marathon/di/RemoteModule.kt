package com.sportunity.marathon.di

import com.sportunity.marathon.data.remote.service.MarathonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideArtObjectApi(
        retrofitBuilder: Retrofit.Builder,
    ): MarathonService {
        return retrofitBuilder
            .build()
            .create(MarathonService::class.java)
    }
}