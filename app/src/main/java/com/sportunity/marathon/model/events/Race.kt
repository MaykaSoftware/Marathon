package com.sportunity.marathon.model.events

import com.sportunity.marathon.model.Statistics
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Race(
    val distance: Double,
    val id: Int,
    val name: String,
    @SerialName("participant_amount_expected")
    val participantAmountExpected: Int?,
    @SerialName("participant_count")
    val participantCount: Int,
    val sport: String,
    val start: String,
    @SerialName("start_type")
    val startType: String,
    val state: String,
    val statistics: Statistics,
    val timetable: Timetable?
)