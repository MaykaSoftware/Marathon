package com.sportunity.marathon.data.local.entity.events

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Calendar

@Entity
@Serializable
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val idDB: Int = 0,
    val id: Int,
//    val allSports: List<String>,
    val city: String?,
//    val colorPrimary: String,
//    val colorSecondary: String,
//    val country: String,
    val dateFrom: String?,
//    val dateTo: String,
//    val description: String?,
//    val distanceUnits: String,
//    val houseNumber: String?,
//    val imageUrl: String,
//    val liveTrackingText: String,
//    val liveTrackingTitle: String,
//    val logoUrl: String,
    val name: String,
//    val participantAmountExpected: Int?,
//    val postalCode: String?,
//    val state: String,
//    val street: String?,
//    val temperatureUnits: String,
//    val timelineWelcomeImageUrl: String,
//    val timelineWelcomeText: String,
//    val timelineWelcomeTitle: String,
//    val trackingImageUrl: String,
//    val type: String
)

fun String.toDate(): String {
    val cal: Calendar = Calendar.getInstance()
    return SimpleDateFormat("EEEE, dd MMMM yyyy").format(cal.time)
}
