package com.sportunity.marathon.data.mapper

import com.sportunity.marathon.data.local.entity.common.StatisticsEntity
import com.sportunity.marathon.data.local.entity.events.ItemEntity
import com.sportunity.marathon.data.local.entity.events.RaceEntity
import com.sportunity.marathon.data.local.entity.events.TimetableEntity
import com.sportunity.marathon.data.local.entity.events.TimetableXEntity
import com.sportunity.marathon.data.local.entity.events.toDate
import com.sportunity.marathon.data.remote.dto.Statistics
import com.sportunity.marathon.data.remote.dto.events.Item
import com.sportunity.marathon.data.remote.dto.events.Race
import com.sportunity.marathon.data.remote.dto.events.Timetable
import com.sportunity.marathon.data.remote.dto.events.TimetableX
import com.sportunity.marathon.domain.model.Marathon

fun Item.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = id,
//        allSports = allSports,
        city = city,
//        colorPrimary = colorPrimary,
//        colorSecondary = colorSecondary,
//        country = country,
        dateFrom = dateFrom,
//        dateTo = dateTo,
//        description = description,
//        distanceUnits = distanceUnits,
//        houseNumber = houseNumber,
//        imageUrl = imageUrl,
//        liveTrackingText = liveTrackingText,
//        liveTrackingTitle = liveTrackingTitle,
//        logoUrl = logoUrl,
        name = name
//        participantAmountExpected = participantAmountExpected,
//        postalCode = postalCode,
//        state = state,
//        street = street,
//        temperatureUnits = temperatureUnits,
//        timelineWelcomeImageUrl = timelineWelcomeImageUrl,
//        timelineWelcomeText = timelineWelcomeText,
//        timelineWelcomeTitle = timelineWelcomeTitle,
//        trackingImageUrl = trackingImageUrl,
//        type = type
    )
}

fun Race.toRacesEntity(): RaceEntity {
    return RaceEntity(
        distance = distance,
        id = id,
        name = name,
        participantAmountExpected = participantAmountExpected,
        participantCount = participantCount,
        sport = sport,
        start = start,
        startType = startType,
        state = state,
        statistics = statistics.toStatisticsEntity(),
        timetable = timetable?.toTimeTableEntity()
    )
}

fun Statistics.toStatisticsEntity(): StatisticsEntity {
    return StatisticsEntity(
        averageAge = averageAge,
        femaleParticipantsCount = femaleParticipantsCount,
        maleParticipantsCount = maleParticipantsCount,
        totalParticipantsCount = totalParticipantsCount
    )
}

fun Timetable.toTimeTableEntity(): TimetableEntity {
    return TimetableEntity(icon = icon,
        name = name,
        published = published,
        timetable = timetable.map {
            it.toTimeTableXEntity()
        })
}

fun TimetableX.toTimeTableXEntity(): TimetableXEntity {
    return TimetableXEntity(
        icon = icon, index = index, subtitle = subtitle, time = time, title = title
    )
}

fun ItemEntity.toMarathon(): Marathon {
    return Marathon(
        id = id,
        city = city,
//        country = country,
        dateFrom = dateFrom,
//        dateTo = dateTo,
//        description = description,
//        imageUrl = imageUrl,
        name = name
//        state = state

    )
}