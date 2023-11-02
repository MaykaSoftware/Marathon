package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.Serializable

@Serializable
data class Timetable(
    val icon: String,
    val name: String,
    val published: Boolean,
    val timetable: List<TimetableX>
)