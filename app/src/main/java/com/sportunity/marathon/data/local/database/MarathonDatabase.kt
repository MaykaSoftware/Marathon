package com.sportunity.marathon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sportunity.marathon.data.local.entity.events.ItemEntity
import com.sportunity.marathon.data.local.typeconverter.events.MarathonEventsTypeConverter

@Database(entities = [ItemEntity::class], version = 1)
@TypeConverters(MarathonEventsTypeConverter::class)
abstract class MarathonDatabase : RoomDatabase(){

    companion object {
        const val DATABASE_NAME = "Marathon Database"
    }
}