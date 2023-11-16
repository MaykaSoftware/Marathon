package com.sportunity.marathon.data.local.entity.event

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val features: List<Feature>,
    val type: String
)