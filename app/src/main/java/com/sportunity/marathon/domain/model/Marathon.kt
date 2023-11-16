package com.sportunity.marathon.domain.model

import androidx.room.PrimaryKey

class Marathon(
    @PrimaryKey
    val id: Int,
    val city: String?,
//    val country: String,
    val dateFrom: String?,
//    val dateTo: String,
    val description: String? = "",
    val imageUrl: String?,
    val name: String,
//    val state: String,
)