package com.sportunity.marathon.domain.usecase

import com.sportunity.marathon.data.mapper.toMarathonRace
import com.sportunity.marathon.domain.model.MarathonRace
import com.sportunity.marathon.domain.repository.MarathonRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarathonRaceUseCase@Inject constructor(
    private val marathonRepository: MarathonRepository
) {
    operator fun invoke(id: Int) = flow<RaceEventResult> {
        emit(RaceEventResult.Loading)

        emit(RaceEventResult.Success(marathonRepository.getRace(id).toMarathonRace()))
    }
}

sealed class RaceEventResult {
    data class Success(val event: MarathonRace): RaceEventResult()
    data class Error(val error: ERROR) : RaceEventResult()

    data object Loading: RaceEventResult()
}

enum class ERROR {
    HTTP_ERROR,
    IO_ERROR,
    UNKNOWN_ERROR
}