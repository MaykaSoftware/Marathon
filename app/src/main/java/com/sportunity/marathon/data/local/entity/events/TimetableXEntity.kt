package com.sportunity.marathon.data.local.entity.events

import kotlinx.serialization.Serializable

@Serializable
data class TimetableXEntity(
    val icon: String,
    val index: Int,
    val subtitle: String,
    val time: String,
    val title: String
)