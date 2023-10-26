package com.sportunity.marathon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistics(
    @SerialName("average_age")
    val averageAge: Int,
    @SerialName("female_participants_count")
    val femaleParticipantsCount: Int,
    @SerialName("male_participants_count")
    val maleParticipantsCount: Int,
    @SerialName("total_participants_count")
    val totalParticipantsCount: Int
)

/**
 * Deleted @property female_record
 * and @property male_record
 * because all values are null and cannot extract type of these properties
 * */