package com.sportunity.marathon.data.local.entity.events

import com.sportunity.marathon.data.local.entity.common.StatisticsEntity
import kotlinx.serialization.Serializable

@Serializable
data class RaceEntity(
    val distance: Double,
    val id: Int,
    val name: String,
    val participantAmountExpected: Int?,
    val participantCount: Int,
    val sport: String,
    val start: String,
    val startType: String,
    val state: String,
    val statistics: StatisticsEntity,
    val timetable: TimetableEntity?
)