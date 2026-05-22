package com.example.astrologyapp

import android.app.Application
import androidx.room.Room
import com.example.astrologyapp.data.local.AstrologyCalculator
import com.example.astrologyapp.data.local.ChartDatabase
import com.example.astrologyapp.data.repository.ChartRepository

class AstrologyApp : Application() {
    
    lateinit var database: ChartDatabase
        private set
        
    lateinit var repository: ChartRepository
        private set
        
    override fun onCreate() {
        super.onCreate()
        
        database = Room.databaseBuilder(
            this,
            ChartDatabase::class.java,
            "astrology_db"
        ).build()
        
        val calculator = AstrologyCalculator()
        repository = ChartRepository(database.chartDao(), calculator)
    }
}
