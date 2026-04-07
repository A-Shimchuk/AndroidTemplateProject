package com.example.androidtemplateproject.screens.detailScreen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AppDetailsEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }

}