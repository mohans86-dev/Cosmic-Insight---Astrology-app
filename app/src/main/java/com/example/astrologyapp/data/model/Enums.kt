package com.example.astrologyapp.data.model

enum class Planet(val displayName: String, val sanskritName: String, val abbreviation: String, val symbol: String) {
    SUN("Sun", "Surya", "Su", "☉"),
    MOON("Moon", "Chandra", "Mo", "☽"),
    MARS("Mars", "Mangal", "Ma", "♂"),
    MERCURY("Mercury", "Budh", "Me", "☿"),
    JUPITER("Jupiter", "Guru", "Ju", "♃"),
    VENUS("Venus", "Shukra", "Ve", "♀"),
    SATURN("Saturn", "Shani", "Sa", "♄"),
    RAHU("Rahu", "Rahu", "Ra", "☊"),
    KETU("Ketu", "Ketu", "Ke", "☋")
}

enum class ZodiacSign(
    val displayName: String, 
    val sanskritName: String, 
    val abbreviation: String,
    val element: Element, 
    val quality: Quality, 
    val rulingPlanet: Planet,
    val startDegree: Double,
    val symbol: String
) {
    ARIES("Aries", "Mesha", "Ar", Element.FIRE, Quality.CARDINAL, Planet.MARS, 0.0, "♈"),
    TAURUS("Taurus", "Vrishabha", "Ta", Element.EARTH, Quality.FIXED, Planet.VENUS, 30.0, "♉"),
    GEMINI("Gemini", "Mithuna", "Ge", Element.AIR, Quality.MUTABLE, Planet.MERCURY, 60.0, "♊"),
    CANCER("Cancer", "Karka", "Ca", Element.WATER, Quality.CARDINAL, Planet.MOON, 90.0, "♋"),
    LEO("Leo", "Simha", "Le", Element.FIRE, Quality.FIXED, Planet.SUN, 120.0, "♌"),
    VIRGO("Virgo", "Kanya", "Vi", Element.EARTH, Quality.MUTABLE, Planet.MERCURY, 150.0, "♍"),
    LIBRA("Libra", "Tula", "Li", Element.AIR, Quality.CARDINAL, Planet.VENUS, 180.0, "♎"),
    SCORPIO("Scorpio", "Vrishchika", "Sc", Element.WATER, Quality.FIXED, Planet.MARS, 210.0, "♏"),
    SAGITTARIUS("Sagittarius", "Dhanu", "Sg", Element.FIRE, Quality.MUTABLE, Planet.JUPITER, 240.0, "♐"),
    CAPRICORN("Capricorn", "Makara", "Cp", Element.EARTH, Quality.CARDINAL, Planet.SATURN, 270.0, "♑"),
    AQUARIUS("Aquarius", "Kumbha", "Aq", Element.AIR, Quality.FIXED, Planet.SATURN, 300.0, "♒"),
    PISCES("Pisces", "Meena", "Pi", Element.WATER, Quality.MUTABLE, Planet.JUPITER, 330.0, "♓");

    companion object {
        fun fromDegree(degree: Double): ZodiacSign {
            val normalized = ((degree % 360) + 360) % 360
            return entries.lastOrNull { normalized >= it.startDegree } ?: PISCES
        }
    }
}

enum class Element(val displayName: String) {
    FIRE("Fire"), EARTH("Earth"), AIR("Air"), WATER("Water")
}

enum class Quality(val displayName: String) {
    CARDINAL("Cardinal"), FIXED("Fixed"), MUTABLE("Mutable")
}

enum class Gender {
    MALE, FEMALE, OTHER
}

enum class Nakshatra(val displayName: String, val rulingPlanet: Planet, val startDegree: Double, val endDegree: Double) {
    ASHWINI("Ashwini", Planet.KETU, 0.0, 13.333),
    BHARANI("Bharani", Planet.VENUS, 13.333, 26.667),
    KRITTIKA("Krittika", Planet.SUN, 26.667, 40.0),
    ROHINI("Rohini", Planet.MOON, 40.0, 53.333),
    MRIGASHIRA("Mrigashira", Planet.MARS, 53.333, 66.667),
    ARDRA("Ardra", Planet.RAHU, 66.667, 80.0),
    PUNARVASU("Punarvasu", Planet.JUPITER, 80.0, 93.333),
    PUSHYA("Pushya", Planet.SATURN, 93.333, 106.667),
    ASHLESHA("Ashlesha", Planet.MERCURY, 106.667, 120.0),
    MAGHA("Magha", Planet.KETU, 120.0, 133.333),
    PURVA_PHALGUNI("Purva Phalguni", Planet.VENUS, 133.333, 146.667),
    UTTARA_PHALGUNI("Uttara Phalguni", Planet.SUN, 146.667, 160.0),
    HASTA("Hasta", Planet.MOON, 160.0, 173.333),
    CHITRA("Chitra", Planet.MARS, 173.333, 186.667),
    SWATI("Swati", Planet.RAHU, 186.667, 200.0),
    VISHAKHA("Vishakha", Planet.JUPITER, 200.0, 213.333),
    ANURADHA("Anuradha", Planet.SATURN, 213.333, 226.667),
    JYESHTHA("Jyeshtha", Planet.MERCURY, 226.667, 240.0),
    MULA("Mula", Planet.KETU, 240.0, 253.333),
    PURVA_ASHADHA("Purva Ashadha", Planet.VENUS, 253.333, 266.667),
    UTTARA_ASHADHA("Uttara Ashadha", Planet.SUN, 266.667, 280.0),
    SHRAVANA("Shravana", Planet.MOON, 280.0, 293.333),
    DHANISHTA("Dhanishta", Planet.MARS, 293.333, 306.667),
    SHATABHISHA("Shatabhisha", Planet.RAHU, 306.667, 320.0),
    PURVA_BHADRAPADA("Purva Bhadrapada", Planet.JUPITER, 320.0, 333.333),
    UTTARA_BHADRAPADA("Uttara Bhadrapada", Planet.SATURN, 333.333, 346.667),
    REVATI("Revati", Planet.MERCURY, 346.667, 360.0);

    companion object {
        fun fromDegree(degree: Double): Nakshatra {
            val normalized = ((degree % 360) + 360) % 360
            return entries.firstOrNull { normalized >= it.startDegree && normalized < it.endDegree } ?: REVATI
        }
    }
}
