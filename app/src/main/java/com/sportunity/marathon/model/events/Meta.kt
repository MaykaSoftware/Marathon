package com.sportunity.marathon.model.events

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val pagination: Pagination
)