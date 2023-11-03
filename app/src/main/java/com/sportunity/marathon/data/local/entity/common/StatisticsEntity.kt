package com.sportunity.marathon.data.local.entity.common

import kotlinx.serialization.Serializable

@Serializable
data class StatisticsEntity(
    val averageAge: Int,
    val femaleParticipantsCount: Int,
    val maleParticipantsCount: Int,
    val totalParticipantsCount: Int
)