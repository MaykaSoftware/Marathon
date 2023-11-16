package com.sportunity.marathon.domain.model

import com.google.android.gms.maps.model.LatLng


data class MarathonRace(
    val name: String,
    val raceName: String?,
    val raceDistance: Int,
    val date: String,
    val countOfParticipants: Int,
    val coordinates: List<LatLng>
)
