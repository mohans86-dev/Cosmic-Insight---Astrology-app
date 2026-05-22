package com.example.astrologyapp.data.model

data class BirthChart(
    val id: Long = 0,
    val birthDetails: BirthDetails,
    val ascendant: ZodiacSign,
    val ascendantDegree: Double,
    val planets: List<PlanetPosition>,
    val houses: List<HouseInfo>,
    val dashas: List<DashaPeriod>,
    val generatedAt: Long = System.currentTimeMillis()
)
