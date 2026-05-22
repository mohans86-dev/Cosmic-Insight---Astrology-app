package com.example.astrologyapp.data.local

import com.example.astrologyapp.data.model.*
import com.example.astrologyapp.util.AstronomyConstants
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.*

class AstrologyCalculator {
    fun calculateBirthChart(birthDetails: BirthDetails): BirthChart {
        val jd = toJulianDay(birthDetails.dateOfBirth, birthDetails.timeOfBirth, birthDetails.timezone)
        
        val ascendantPair = calculateAscendant(jd, birthDetails.latitude, birthDetails.longitude)
        val ascendant = ascendantPair.first
        val ascendantDegree = ascendantPair.second
        
        val planets = calculatePlanetaryPositions(jd, ascendantDegree)
        val houses = calculateHouses(ascendantDegree)
        
        val moonPosition = planets.find { it.planet == Planet.MOON }?.degree ?: 0.0
        val dashas = calculateDashas(moonPosition, birthDetails.dateOfBirth)
        
        return BirthChart(
            id = 0,
            birthDetails = birthDetails,
            ascendant = ascendant,
            ascendantDegree = ascendantDegree,
            planets = planets,
            houses = houses,
            dashas = dashas,
            generatedAt = System.currentTimeMillis()
        )
    }
    
    private fun calculatePlanetaryPositions(julianDay: Double, ascendantDegree: Double): List<PlanetPosition> {
        val positions = mutableListOf<PlanetPosition>()
        val ayanamsa = calculateAyanamsa(julianDay)
        
        for (planet in Planet.entries) {
            // Simplified position
            val tropicalDegree = calculatePlanetTropicalDegree(planet, julianDay)
            
            // Convert to sidereal using Lahiri Ayanamsa
            var siderealDegree = (tropicalDegree - ayanamsa) % 360.0
            if (siderealDegree < 0) siderealDegree += 360.0
            
            val sign = ZodiacSign.fromDegree(siderealDegree)
            val nakshatra = Nakshatra.fromDegree(siderealDegree)
            
            // House (Simplified Equal House System based on ascendant)
            val diff = siderealDegree - ascendantDegree
            val normalizedDiff = (diff + 360) % 360
            val house = (normalizedDiff / 30.0).toInt() + 1
            
            val degreeInSign = siderealDegree % 30.0
            
            positions.add(PlanetPosition(
                planet = planet,
                sign = sign,
                degree = degreeInSign,
                house = house,
                nakshatra = nakshatra,
                isRetrograde = isRetrograde(planet, julianDay)
            ))
        }
        return positions
    }
    
    private fun calculatePlanetTropicalDegree(planet: Planet, jd: Double): Double {
        val daysSinceJ2000 = jd - AstronomyConstants.J2000
        
        // Very simplified mean motion approximation for entertainment purposes
        val meanDegree = when (planet) {
            Planet.SUN -> (280.460 + 0.9856474 * daysSinceJ2000)
            Planet.MOON -> (218.316 + 13.176396 * daysSinceJ2000)
            Planet.MARS -> (355.453 + 0.5240207 * daysSinceJ2000)
            Planet.MERCURY -> (252.250 + 4.092317 * daysSinceJ2000)
            Planet.JUPITER -> (34.404 + 0.0830853 * daysSinceJ2000)
            Planet.VENUS -> (50.115 + 1.6021302 * daysSinceJ2000)
            Planet.SATURN -> (50.077 + 0.0334442 * daysSinceJ2000)
            Planet.RAHU -> (125.044 - 0.0529537 * daysSinceJ2000)
            Planet.KETU -> (125.044 - 0.0529537 * daysSinceJ2000 + 180.0) // Opposite to Rahu
        }
        
        return ((meanDegree % 360) + 360) % 360
    }
    
    private fun isRetrograde(planet: Planet, jd: Double): Boolean {
        // Simplified dummy logic for retrograde (only true sometimes for some planets)
        if (planet == Planet.SUN || planet == Planet.MOON) return false
        if (planet == Planet.RAHU || planet == Planet.KETU) return true
        
        // Very fake logic: just depend on the day mod something
        val dayInt = jd.toInt()
        return when (planet) {
            Planet.MERCURY -> dayInt % 116 < 22
            Planet.VENUS -> dayInt % 584 < 42
            Planet.MARS -> dayInt % 780 < 72
            Planet.JUPITER -> dayInt % 399 < 121
            Planet.SATURN -> dayInt % 378 < 138
            else -> false
        }
    }
    
    private fun calculateAscendant(julianDay: Double, latitude: Double, longitude: Double): Pair<ZodiacSign, Double> {
        val daysSinceJ2000 = julianDay - AstronomyConstants.J2000
        val gst = (280.46061837 + 360.98564736629 * daysSinceJ2000) % 360.0
        var lst = (gst + longitude) % 360.0
        if (lst < 0) lst += 360.0
        
        val radLST = lst * AstronomyConstants.DEG_TO_RAD
        val radLat = latitude * AstronomyConstants.DEG_TO_RAD
        val obliquity = 23.439 * AstronomyConstants.DEG_TO_RAD // Simplification
        
        var ascendantRad = atan2(cos(radLST), -sin(radLST) * cos(obliquity) - tan(radLat) * sin(obliquity))
        if (ascendantRad < 0) ascendantRad += 2 * Math.PI
        
        val tropicalAscendant = ascendantRad * AstronomyConstants.RAD_TO_DEG
        val ayanamsa = calculateAyanamsa(julianDay)
        
        var siderealAscendant = (tropicalAscendant - ayanamsa) % 360.0
        if (siderealAscendant < 0) siderealAscendant += 360.0
        
        return Pair(ZodiacSign.fromDegree(siderealAscendant), siderealAscendant)
    }
    
    private fun calculateHouses(ascendantDegree: Double): List<HouseInfo> {
        val houses = mutableListOf<HouseInfo>()
        for (i in 0 until 12) {
            val startDegree = (ascendantDegree + i * 30.0) % 360.0
            houses.add(HouseInfo(i + 1, ZodiacSign.fromDegree(startDegree), startDegree))
        }
        return houses
    }
    
    private fun calculateDashas(moonDegree: Double, birthDate: LocalDate): List<DashaPeriod> {
        val nakshatra = Nakshatra.fromDegree(moonDegree)
        val nakshatraExtent = 13.333
        val degreePassed = moonDegree - nakshatra.startDegree
        val fractionPassed = degreePassed / nakshatraExtent
        
        val firstPlanet = nakshatra.rulingPlanet
        val totalYears = DashaPeriod.DASHA_YEARS[firstPlanet] ?: 0
        val yearsPassed = fractionPassed * totalYears
        val yearsRemaining = totalYears - yearsPassed
        
        val periods = mutableListOf<DashaPeriod>()
        var currentDate = birthDate
        
        // Sequence of Dasha lords
        val dashaSequence = listOf(
            Planet.KETU, Planet.VENUS, Planet.SUN, Planet.MOON, Planet.MARS,
            Planet.RAHU, Planet.JUPITER, Planet.SATURN, Planet.MERCURY
        )
        
        val startIndex = dashaSequence.indexOf(firstPlanet)
        
        // First period (partial)
        val firstPeriodEnd = currentDate.plusDays((yearsRemaining * 365.25).toLong())
        periods.add(DashaPeriod(firstPlanet, currentDate, firstPeriodEnd, null))
        currentDate = firstPeriodEnd
        
        // Remaining 8 periods
        for (i in 1..8) {
            val nextPlanetIndex = (startIndex + i) % 9
            val planet = dashaSequence[nextPlanetIndex]
            val years = DashaPeriod.DASHA_YEARS[planet] ?: 0
            val endDate = currentDate.plusDays((years * 365.25).toLong())
            periods.add(DashaPeriod(planet, currentDate, endDate, null))
            currentDate = endDate
        }
        
        return periods
    }
    
    private fun calculateAyanamsa(julianDay: Double): Double {
        // Lahiri Ayanamsa approximation
        val t = (julianDay - AstronomyConstants.J2000) / 36525.0
        return 23.85 + (t * 1.397)
    }
    
    private fun toJulianDay(date: LocalDate, time: LocalTime, timezone: String): Double {
        // Simplified Julian Day calculation ignoring timezone complexity for now
        var y = date.year
        var m = date.monthValue
        val d = date.dayOfMonth
        
        if (m <= 2) {
            y -= 1
            m += 12
        }
        
        val a = y / 100
        val b = 2 - a + a / 4
        
        val jdDate = (365.25 * (y + 4716)).toLong() + (30.6001 * (m + 1)).toLong() + d + b - 1524.5
        val jdTime = (time.hour + time.minute / 60.0 + time.second / 3600.0) / 24.0
        
        return jdDate + jdTime
    }
}
