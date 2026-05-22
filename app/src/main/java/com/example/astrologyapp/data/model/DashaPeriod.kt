package com.example.astrologyapp.data.model

import java.time.LocalDate

data class DashaPeriod(
    val planet: Planet,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val subPeriods: List<DashaPeriod>?
) {
    companion object {
        val DASHA_YEARS: Map<Planet, Int> = mapOf(
            Planet.SUN to 6,
            Planet.MOON to 10,
            Planet.MARS to 7,
            Planet.RAHU to 18,
            Planet.JUPITER to 16,
            Planet.SATURN to 19,
            Planet.MERCURY to 17,
            Planet.KETU to 7,
            Planet.VENUS to 20
        )
    }
}
