package com.sportunity.marathon.data.remote.dto.event

import com.sportunity.marathon.data.remote.dto.Statistics
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarathonRace(
    val distance: Int,
    val id: Int,
    val name: String,
    @SerialName("participant_count")
    val participantCount: Int,
    val route: Route,
    val sport: String,
    val start: String,
    @SerialName("start_type")
    val startType: String,
    val state: String,
    val statistics: Statistics,
)