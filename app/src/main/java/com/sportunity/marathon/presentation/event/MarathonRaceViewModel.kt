package com.sportunity.marathon.presentation.event

import android.location.Location
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.sportunity.marathon.domain.model.MarathonRace
import com.sportunity.marathon.domain.usecase.ERROR
import com.sportunity.marathon.domain.usecase.GetLocationUpdateUseCase
import com.sportunity.marathon.domain.usecase.MarathonRaceUseCase
import com.sportunity.marathon.domain.usecase.RaceEventResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarathonRaceViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val marathonRaceUseCase: MarathonRaceUseCase,
    getLocationUpdateUseCase: GetLocationUpdateUseCase
) : ViewModel() {
    private val mutableState = MutableStateFlow<State>(State())
    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            val racIid = savedStateHandle.get<String>("id") ?: return@launch
            marathonRaceUseCase.invoke(racIid.toInt()).collect { result ->
                when (result) {
                    is RaceEventResult.Error -> {
                        when (result.error) {
                            ERROR.HTTP_ERROR -> {
                                mutableState.value =
                                    state.value.copy(errorMessage = "Something went wrong")
                            }

                            ERROR.IO_ERROR -> {
                                mutableState.value =
                                    state.value.copy(errorMessage = "There is no internet connection")
                            }

                            ERROR.UNKNOWN_ERROR -> {
                                mutableState.value =
                                    state.value.copy(errorMessage = "Empty object number")
                            }
                        }
                    }

                    RaceEventResult.Loading -> {
                        mutableState.value = state.value.copy(isLoading = true)
                    }

                    is RaceEventResult.Success -> {
                        mutableState.value = state.value.copy(marathonRace = result.event, startLocation = LatLng(
                            result.event.coordinates[0].latitude,
                            result.event.coordinates[0].longitude
                        ))
                    }
                }
            }
        }

        getLocationUpdateUseCase.invoke().onEach {
            mutableState.value = state.value.copy(currentLocation = it)
        }.launchIn(viewModelScope)

    }
}

data class State(
    val isLoading: Boolean = false,
    val marathonRace: MarathonRace = MarathonRace(),
    val startLocation: LatLng = LatLng(0.0, 0.0),
    val currentLocation: Location? = null,
    val errorMessage: String = ""
)
