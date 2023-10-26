package com.sportunity.marathon.model.events

import kotlinx.serialization.Serializable

@Serializable
data class MarathonEvents(
    val items: List<Item>,
    val meta: Meta
)