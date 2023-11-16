package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val next: String?,
    val previous: String?
)