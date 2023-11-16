package com.sportunity.marathon.data.mapper

import com.google.android.gms.maps.model.LatLng
import com.sportunity.marathon.data.remote.dto.event.MarathonRaceDTO
import com.sportunity.marathon.domain.model.MarathonRace
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun MarathonRaceDTO.toMarathonRace(): MarathonRace {
    return MarathonRace(
        name = name,
        raceName = route.features[0].properties?.name,
        raceDistance = convertToKm(),
        date  = start.toDate(),
        countOfParticipants = participantCount,
        coordinates = route.features[0].geometry.coordinates.map {
            LatLng(
                it[1],
                it[0]
            )
        }
    )
}

fun MarathonRaceDTO.convertToKm(): Int {
    val km =  this.distance/1000
    return km.toInt()
}


fun String.toDate(): String {
    val date = ZonedDateTime.parse(this)
    return DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm").format(date)
}