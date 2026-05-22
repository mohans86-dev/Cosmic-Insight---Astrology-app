package com.example.astrologyapp.data.model

object PlanetInterpretation {
    fun getInterpretation(planet: Planet, sign: ZodiacSign): String {
        // Simplified generic interpretations for a standard app
        val planetSignificance = when (planet) {
            Planet.SUN -> "The Sun represents your core identity, ego, and life path."
            Planet.MOON -> "The Moon represents your emotional inner world, instincts, and subconscious."
            Planet.MARS -> "Mars governs your drive, energy, ambition, and how you assert yourself."
            Planet.MERCURY -> "Mercury influences your communication style, intellect, and reasoning."
            Planet.JUPITER -> "Jupiter represents expansion, luck, philosophy, and growth."
            Planet.VENUS -> "Venus governs love, beauty, values, and what you attract."
            Planet.SATURN -> "Saturn represents discipline, responsibility, challenges, and life lessons."
            Planet.RAHU -> "Rahu indicates your worldly desires, obsessions, and unconventional pursuits."
            Planet.KETU -> "Ketu represents your past life karma, spirituality, and detachment."
        }

        val signModifier = when (sign) {
            ZodiacSign.ARIES -> "In Aries, it expresses itself with fiery independence, impulsiveness, and courage."
            ZodiacSign.TAURUS -> "In Taurus, it takes on a steady, practical, and grounded nature focused on material stability."
            ZodiacSign.GEMINI -> "In Gemini, it manifests through curiosity, adaptability, and intellectual exchange."
            ZodiacSign.CANCER -> "In Cancer, it expresses with emotional depth, nurturing instincts, and a focus on security."
            ZodiacSign.LEO -> "In Leo, it shines with creativity, dramatic flair, and a need for recognition."
            ZodiacSign.VIRGO -> "In Virgo, it becomes analytical, service-oriented, and focused on meticulous improvement."
            ZodiacSign.LIBRA -> "In Libra, it seeks balance, harmony, and expresses through partnerships and aesthetic appreciation."
            ZodiacSign.SCORPIO -> "In Scorpio, it dives into intense, transformative, and probing emotional depths."
            ZodiacSign.SAGITTARIUS -> "In Sagittarius, it expands into philosophical pursuits, adventure, and seeking higher truth."
            ZodiacSign.CAPRICORN -> "In Capricorn, it channels into disciplined ambition, structure, and achieving long-term goals."
            ZodiacSign.AQUARIUS -> "In Aquarius, it expresses through innovative, humanitarian, and unconventional approaches."
            ZodiacSign.PISCES -> "In Pisces, it dissolves boundaries, acting with compassion, intuition, and spiritual sensitivity."
        }

        return "$planetSignificance $signModifier"
    }
}
