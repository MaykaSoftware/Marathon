package com.sportunity.marathon.data.local.entity.events

import kotlinx.serialization.Serializable

@Serializable
data class TimetableEntity(
    val icon: String,
    val name: String,
    val published: Boolean,
    val timetable: List<TimetableXEntity>
)