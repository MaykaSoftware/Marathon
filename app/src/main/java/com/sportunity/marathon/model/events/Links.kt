package com.sportunity.marathon.model.events

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val next: String?,
    val previous: String?
)