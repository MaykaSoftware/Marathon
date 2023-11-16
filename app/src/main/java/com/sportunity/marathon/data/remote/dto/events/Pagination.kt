package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val count: Int,
    @SerialName("current_page")
    val currentPage: Int,
    val links: Links,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int
)

/**
 * Deleted @property filters
 * because all arrays are empty and cannot extract type of this list
 * */