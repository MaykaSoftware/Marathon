package com.sportunity.marathon.domain.usecase

import androidx.paging.PagingData
import com.sportunity.marathon.domain.model.Marathon
import com.sportunity.marathon.domain.repository.MarathonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class MarathonEventsUseCase @Inject constructor(
    private val marathonRepository: MarathonRepository
) {
    operator fun invoke(): Flow<PagingData<Marathon>> =
        marathonRepository.marathonEvents.catch {
            emptyList<Marathon>()
        }
}