package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val pagination: Pagination
)