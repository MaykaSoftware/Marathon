package com.sportunity.marathon.data.local.typeconverter.events

import androidx.room.TypeConverter
import com.sportunity.marathon.data.local.entity.common.StatisticsEntity
import com.sportunity.marathon.data.local.entity.events.RaceEntity
import com.sportunity.marathon.data.local.entity.events.TimetableEntity
import com.sportunity.marathon.data.local.entity.events.TimetableXEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MarathonEventsTypeConverter {
    @TypeConverter
    fun convertRaceEntityToJsonString(raceEntity: List<RaceEntity>?): String =
        Json.encodeToString(raceEntity)

    @TypeConverter
    fun convertRaceEntityToObject(json: String): List<RaceEntity>? =
        Json.decodeFromString(json)

    @TypeConverter
    fun convertAllSportsToJsonString(allSports: List<String>?): String =
        Json.encodeToString(allSports)

    @TypeConverter
    fun convertAllSportsToObject(json: String): List<String>? =
        Json.decodeFromString(json)

    @TypeConverter
    fun convertStatisticsEntityToJsonString(statisticsEntity: StatisticsEntity?): String =
        Json.encodeToString(statisticsEntity)


    @TypeConverter
    fun convertStatisticsEntityToObject(json: String): StatisticsEntity? =
        Json.decodeFromString(json)

    @TypeConverter
    fun convertTimetableEntityToJsonString(timetableEntity: TimetableEntity?): String =
        Json.encodeToString(timetableEntity)


    @TypeConverter
    fun convertTimetableEntityToObject(json: String): TimetableEntity? =
        Json.decodeFromString(json)

    @TypeConverter
    fun convertTimetableXEntityToJsonString(timetableXEntity: List<TimetableXEntity>?): String =
        Json.encodeToString(timetableXEntity)

    @TypeConverter
    fun convertTimetableXEntityToObject(json: String): List<TimetableXEntity>? =
        Json.decodeFromString(json)
}