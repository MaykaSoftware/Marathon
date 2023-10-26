package com.sportunity.marathon.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    val coordinates: List<List<Double>>,
    val type: String
)