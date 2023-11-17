package com.sportunity.marathon.domain.model

import com.google.android.gms.maps.model.LatLng


data class MarathonRace(
    val name: String = "",
    val raceName: String = "",
    val raceDistance: Int = 0,
    val date: String = "",
    val countOfParticipants: Int = 0,
    val coordinates: List<LatLng> = emptyList()
)
