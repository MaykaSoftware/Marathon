package com.sportunity.marathon.data.local.entity.events

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sportunity.marathon.R
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val idDB: Int = 0,
    val id: Int,
//    val allSports: List<String>,
    val city: String?,
    val dateFrom: String?,
//    val description: String?,
    val imageUrl: Int,
    val name: String,
)

fun randomImage(): Int {
    return when((1..3).shuffled().first()){
        1 -> R.drawable.marathon
        2 -> R.drawable.cycling
        3 -> R.drawable.swimming
        else -> {
            R.drawable.marathon
        }
    }
}
