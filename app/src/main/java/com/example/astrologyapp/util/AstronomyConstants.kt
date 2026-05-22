package com.example.astrologyapp.util

object AstronomyConstants {
    // Julian Day calculation constants
    const val J2000 = 2451545.0
    
    // Mean orbital periods in days
    const val TROPICAL_YEAR = 365.2421904
    const val SIDEREAL_YEAR = 365.256363004
    
    // Simplistic mean daily motion (degrees per day) for approximation
    const val SUN_MEAN_MOTION = 0.985647
    const val MOON_MEAN_MOTION = 13.176396
    
    // Other constants
    const val DEG_TO_RAD = Math.PI / 180.0
    const val RAD_TO_DEG = 180.0 / Math.PI
}
