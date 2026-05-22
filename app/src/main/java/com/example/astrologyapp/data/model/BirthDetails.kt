package com.example.astrologyapp.data.model

import java.time.LocalDate
import java.time.LocalTime

data class BirthDetails(
    val name: String,
    val dateOfBirth: LocalDate,
    val timeOfBirth: LocalTime,
    val placeOfBirth: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val gender: Gender?
)
