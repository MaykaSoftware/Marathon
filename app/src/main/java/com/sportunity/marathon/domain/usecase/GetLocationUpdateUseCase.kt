package com.sportunity.marathon.domain.usecase

import android.location.Location
import com.sportunity.marathon.domain.repository.MarathonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUpdateUseCase @Inject constructor(
    private val marathonRepository: MarathonRepository
) {

    operator fun invoke(): Flow<Location> {
        return marathonRepository.getLocation()
    }
}