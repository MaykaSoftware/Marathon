package com.sportunity.marathon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class MarathonDatabase : RoomDatabase(){

    companion object {
        const val DATABASE_NAME = "Marathon Database"
    }
}