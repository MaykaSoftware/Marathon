package com.sportunity.marathon.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sportunity.marathon.data.local.database.MarathonDatabase
import com.sportunity.marathon.data.local.entity.events.ItemEntity
import com.sportunity.marathon.data.remote.service.MarathonService
import com.sportunity.marathon.pagination.MarathonRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagerModule {
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideArtObjectPager(
        marathonDatabase: MarathonDatabase,
        marathonService: MarathonService
    ): Pager<Int, ItemEntity> {
        return Pager(
            config = PagingConfig(pageSize = 15),
            remoteMediator = MarathonRemoteMediator(
                marathonDB = marathonDatabase,
                marathonService = marathonService
            ),
            pagingSourceFactory = {
                marathonDatabase.getMarathonEvents().pagingSource()
            }
        )
    }
}