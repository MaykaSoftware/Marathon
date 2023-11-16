package com.sportunity.marathon.data.local.entity.event

import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    val coordinates: List<List<Double>>,
    val type: String
)