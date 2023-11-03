package com.sportunity.marathon.data.local.entity.events

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Pagination(
    val count: Int,
    val currentPage: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val next: String?,
    val previous: String?
)