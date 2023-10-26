package com.sportunity.marathon.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val features: List<Feature>,
    val type: String
)