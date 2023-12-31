package com.sportunity.marathon.domain.model

import androidx.annotation.DrawableRes
import androidx.room.PrimaryKey

class Marathon(
    @PrimaryKey
    val id: Int,
    val city: String?,
    val dateFrom: String?,
    val description: String? = "",
    @DrawableRes
    val imageUrl: Int,
    val name: String
)