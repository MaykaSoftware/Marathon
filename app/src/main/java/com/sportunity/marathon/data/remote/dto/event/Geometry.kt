package com.sportunity.marathon.data.remote.dto.event

import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    val coordinates: List<List<Double>>,
    val type: String
)