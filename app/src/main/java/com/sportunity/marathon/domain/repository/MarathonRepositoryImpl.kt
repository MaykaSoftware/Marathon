package com.sportunity.marathon.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.sportunity.marathon.data.local.dao.MarathonEventDao
import com.sportunity.marathon.data.local.entity.events.ItemEntity
import com.sportunity.marathon.data.mapper.toMarathon
import com.sportunity.marathon.data.remote.service.MarathonService
import com.sportunity.marathon.domain.model.Marathon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarathonRepositoryImpl @Inject constructor(
    pager: Pager<Int, ItemEntity>,
    private val marathonService: MarathonService,
    private val marathonEventDao: MarathonEventDao
): MarathonRepository {
    override val marathonEvents: Flow<PagingData<Marathon>> =
        pager.flow.map {
            it.map { itemEntity ->
                itemEntity.toMarathon()
            }
        }.catch {
            throw it
        }
}