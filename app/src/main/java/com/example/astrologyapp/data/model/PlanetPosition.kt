package com.example.astrologyapp.data.model

data class PlanetPosition(
    val planet: Planet,
    val sign: ZodiacSign,
    val degree: Double,
    val house: Int,
    val nakshatra: Nakshatra,
    val isRetrograde: Boolean
)
