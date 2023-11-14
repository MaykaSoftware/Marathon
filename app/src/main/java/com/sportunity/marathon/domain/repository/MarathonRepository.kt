package com.sportunity.marathon.domain.repository

import androidx.paging.PagingData
import com.sportunity.marathon.domain.model.Marathon
import kotlinx.coroutines.flow.Flow

interface MarathonRepository {
    val marathonEvents: Flow<PagingData<Marathon>>
}