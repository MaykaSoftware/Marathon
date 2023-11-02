package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.Serializable

@Serializable
data class MarathonEvents(
    val items: List<Item>,
    val meta: Meta
)