package com.example.astrologyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChartDao {
    @Insert
    suspend fun insertChart(chart: ChartEntity): Long

    @Query("SELECT * FROM saved_charts ORDER BY createdAt DESC")
    fun getAllCharts(): Flow<List<ChartEntity>>

    @Query("SELECT * FROM saved_charts WHERE id = :id")
    suspend fun getChartById(id: Long): ChartEntity?

    @Query("DELETE FROM saved_charts WHERE id = :id")
    suspend fun deleteChart(id: Long)

    @Query("DELETE FROM saved_charts")
    suspend fun deleteAllCharts()
}
