package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.Serializable

@Serializable
data class TimetableX(
    val icon: String,
    val index: Int,
    val subtitle: String,
    val time: String,
    val title: String
)