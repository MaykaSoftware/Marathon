package com.sportunity.marathon.data.remote.dto.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val city: String?,
    @SerialName("date_from")
    val dateFrom: String?,
    @SerialName("image_url")
    val imageUrl: String?,
    val name: String,
//    @SerialName("all_sports")
//    val allSports: List<String>,
//    @SerialName("color_primary")
//    val colorPrimary: String,
//    @SerialName("color_secondary")
//    val colorSecondary: String,
//    val country: String,
//    @SerialName("date_to")
//    val dateTo: String,
//    val description: String?,
//    @SerialName("distance_units")
//    val distanceUnits: String,
//    @SerialName("house_number")
//    val houseNumber: String?,
//    @SerialName("live_tracking_text")
//    val liveTrackingText: String,
//    @SerialName("live_tracking_title")
//    val liveTrackingTitle: String,
//    @SerialName("logo_url")
//    val logoUrl: String,
//    @SerialName("participant_amount_expected")
//    val participantAmountExpected: Int?,
//    @SerialName("postal_code")
//    val postalCode: String?,
//    val state: String,
//    val street: String?,
//    @SerialName("temperature_units")
//    val temperatureUnits: String,
//    @SerialName("timeline_welcome_image_url")
//    val timelineWelcomeImageUrl: String,
//    @SerialName("timeline_welcome_text")
//    val timelineWelcomeText: String,
//    @SerialName("timeline_welcome_title")
//    val timelineWelcomeTitle: String,
//    @SerialName("tracking_image_url")
//    val trackingImageUrl: String,
//    val type: String
)

/**
 * Deleted @property custom_website_colorscheme
 * and @property start_time
 * because all values are null and cannot extract type of these properties
 * */