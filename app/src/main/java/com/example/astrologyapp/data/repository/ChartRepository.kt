package com.example.astrologyapp.data.repository

import com.example.astrologyapp.data.local.AstrologyCalculator
import com.example.astrologyapp.data.local.ChartDao
import com.example.astrologyapp.data.local.ChartEntity
import com.example.astrologyapp.data.model.BirthChart
import com.example.astrologyapp.data.model.BirthDetails
import com.example.astrologyapp.util.DateTimeUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalTime

class ChartRepository(
    private val chartDao: ChartDao,
    private val calculator: AstrologyCalculator
) {
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, com.google.gson.JsonSerializer<LocalDate> { src, _, _ ->
            com.google.gson.JsonPrimitive(src.toString())
        })
        .registerTypeAdapter(LocalDate::class.java, com.google.gson.JsonDeserializer { json, _, _ ->
            LocalDate.parse(json.asString)
        })
        .registerTypeAdapter(LocalTime::class.java, com.google.gson.JsonSerializer<LocalTime> { src, _, _ ->
            com.google.gson.JsonPrimitive(src.toString())
        })
        .registerTypeAdapter(LocalTime::class.java, com.google.gson.JsonDeserializer { json, _, _ ->
            LocalTime.parse(json.asString)
        })
        .create()

    fun generateChart(birthDetails: BirthDetails): BirthChart {
        return calculator.calculateBirthChart(birthDetails)
    }

    suspend fun saveChart(chart: BirthChart): Long {
        val entity = ChartEntity(
            name = chart.birthDetails.name,
            dateOfBirth = DateTimeUtils.formatDate(chart.birthDetails.dateOfBirth),
            placeOfBirth = chart.birthDetails.placeOfBirth,
            chartJson = chartToJson(chart),
            createdAt = System.currentTimeMillis()
        )
        return chartDao.insertChart(entity)
    }

    fun getSavedCharts(): Flow<List<ChartEntity>> {
        return chartDao.getAllCharts()
    }

    suspend fun getChartById(id: Long): ChartEntity? {
        return chartDao.getChartById(id)
    }

    suspend fun deleteChart(id: Long) {
        chartDao.deleteChart(id)
    }

    fun chartToJson(chart: BirthChart): String {
        return gson.toJson(chart)
    }

    fun chartFromJson(json: String): BirthChart {
        return gson.fromJson(json, BirthChart::class.java)
    }
}
