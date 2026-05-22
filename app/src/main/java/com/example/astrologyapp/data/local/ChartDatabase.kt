package com.example.astrologyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChartEntity::class], version = 1, exportSchema = false)
abstract class ChartDatabase : RoomDatabase() {
    abstract fun chartDao(): ChartDao
}
