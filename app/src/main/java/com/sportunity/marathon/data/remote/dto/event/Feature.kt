package com.sportunity.marathon.data.remote.dto.event

import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    val geometry: Geometry,
    val properties: Properties?,
    val type: String
)