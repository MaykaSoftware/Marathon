package com.sportunity.marathon.domain.repository

import android.location.Location
import androidx.paging.PagingData
import com.sportunity.marathon.data.remote.dto.event.MarathonRaceDTO
import com.sportunity.marathon.domain.model.Marathon
import kotlinx.coroutines.flow.Flow

interface MarathonRepository {
    val marathonEvents: Flow<PagingData<Marathon>>
    suspend fun getRace(id: Int): MarathonRaceDTO

    fun getLocation(): Flow<Location>

}