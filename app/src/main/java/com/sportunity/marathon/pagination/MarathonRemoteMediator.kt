package com.sportunity.marathon.pagination

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sportunity.marathon.data.local.database.MarathonDatabase
import com.sportunity.marathon.data.local.entity.events.ItemEntity
import com.sportunity.marathon.data.mapper.toItemEntity
import com.sportunity.marathon.data.remote.service.MarathonService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class MarathonRemoteMediator @Inject constructor(
    private val marathonDB: MarathonDatabase,
    private val marathonService: MarathonService,
    private val dataStore: DataStore<Preferences>
) : RemoteMediator<Int, ItemEntity>() {

    var loadKey = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemEntity>
    ): MediatorResult {
        try {
            loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    loadKey + 1
                }
            }
            val marathon = marathonService.getEvents(include = "races", page = loadKey)

            println("__LOADKEY = $loadKey")
            marathonDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    marathonDB.getMarathonEvents().clearAll()
                }

                val events = marathon.items.map {
                    it.toItemEntity()
                }
                marathonDB.getMarathonEvents().upsertAll(events)
            }
            return MediatorResult.Success(
                endOfPaginationReached = marathon.meta.pagination.links.next == null
            )
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    object PreferencesKeys {
        val RESULT_CURRENT_PAGE = intPreferencesKey("current page")
    }
}