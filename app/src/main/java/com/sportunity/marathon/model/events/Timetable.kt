package com.sportunity.marathon.model.events

import kotlinx.serialization.Serializable

@Serializable
data class Timetable(
    val icon: String,
    val name: String,
    val published: Boolean,
    val timetable: List<TimetableX>
)