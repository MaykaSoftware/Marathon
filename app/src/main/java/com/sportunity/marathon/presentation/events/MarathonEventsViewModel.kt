package com.sportunity.marathon.presentation.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sportunity.marathon.domain.usecase.MarathonEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarathonEventsViewModel @Inject constructor(
    marathonEventsUseCase: MarathonEventsUseCase
) : ViewModel() {

    val marathonEventsFlow = marathonEventsUseCase.invoke().cachedIn(viewModelScope)
}