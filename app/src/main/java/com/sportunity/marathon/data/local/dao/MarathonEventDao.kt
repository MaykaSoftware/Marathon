package com.sportunity.marathon.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sportunity.marathon.data.local.entity.events.ItemEntity

@Dao
interface MarathonEventDao {

    @Upsert
    suspend fun upsertAll(items: List<ItemEntity>)

    @Query("SELECT * FROM itementity")
    fun pagingSource(): PagingSource<Int, ItemEntity>

    @Query("DELETE FROM itementity")
    suspend fun clearAll()
}