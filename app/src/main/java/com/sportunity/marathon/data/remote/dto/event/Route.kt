package com.sportunity.marathon.data.remote.dto.event

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val features: List<Feature>,
    val type: String
)