package com.example.astrologyapp.data.model

object ZodiacSignInfo {

    fun getPersonality(sign: ZodiacSign): String {
        return when (sign) {
            ZodiacSign.ARIES -> "Aries is dynamic, energetic, and always ready for action. They are natural leaders who love a challenge."
            ZodiacSign.TAURUS -> "Taurus is reliable, patient, and devoted. They appreciate beauty, comfort, and stability in life."
            ZodiacSign.GEMINI -> "Gemini is expressive, quick-witted, and highly sociable. They are endlessly curious about the world."
            ZodiacSign.CANCER -> "Cancer is highly intuitive and sentimental. They care deeply about their family and are incredibly loyal."
            ZodiacSign.LEO -> "Leo is dramatic, creative, and self-confident. They have a warm heart and love to be in the spotlight."
            ZodiacSign.VIRGO -> "Virgo is analytical, kind, and hardworking. They pay attention to the smallest details and are very practical."
            ZodiacSign.LIBRA -> "Libra is diplomatic, gracious, and fair-minded. They value harmony and are highly cooperative."
            ZodiacSign.SCORPIO -> "Scorpio is passionate, stubborn, and resourceful. They are true friends who are brave and deeply emotional."
            ZodiacSign.SAGITTARIUS -> "Sagittarius is generous, idealistic, and has a great sense of humor. They love freedom and travel."
            ZodiacSign.CAPRICORN -> "Capricorn is responsible, disciplined, and has good self-control. They are masters of management."
            ZodiacSign.AQUARIUS -> "Aquarius is progressive, original, and independent. They are deep thinkers and highly intellectual."
            ZodiacSign.PISCES -> "Pisces is compassionate, artistic, and gentle. They are very friendly and often find themselves in the company of very different people."
        }
    }

    fun getStrengths(sign: ZodiacSign): List<String> {
        return when (sign) {
            ZodiacSign.ARIES -> listOf("Courageous", "Determined", "Confident", "Enthusiastic", "Optimistic")
            ZodiacSign.TAURUS -> listOf("Reliable", "Patient", "Practical", "Devoted", "Responsible")
            ZodiacSign.GEMINI -> listOf("Gentle", "Affectionate", "Curious", "Adaptable", "Fast learner")
            ZodiacSign.CANCER -> listOf("Tenacious", "Highly imaginative", "Loyal", "Emotional", "Sympathetic")
            ZodiacSign.LEO -> listOf("Creative", "Passionate", "Generous", "Warm-hearted", "Cheerful")
            ZodiacSign.VIRGO -> listOf("Loyal", "Analytical", "Kind", "Hardworking", "Practical")
            ZodiacSign.LIBRA -> listOf("Cooperative", "Diplomatic", "Gracious", "Fair-minded", "Social")
            ZodiacSign.SCORPIO -> listOf("Resourceful", "Brave", "Passionate", "Stubborn", "A true friend")
            ZodiacSign.SAGITTARIUS -> listOf("Generous", "Idealistic", "Great sense of humor")
            ZodiacSign.CAPRICORN -> listOf("Responsible", "Disciplined", "Self-control", "Good managers")
            ZodiacSign.AQUARIUS -> listOf("Progressive", "Original", "Independent", "Humanitarian")
            ZodiacSign.PISCES -> listOf("Compassionate", "Artistic", "Intuitive", "Gentle", "Wise")
        }
    }

    fun getWeaknesses(sign: ZodiacSign): List<String> {
        return when (sign) {
            ZodiacSign.ARIES -> listOf("Impatient", "Moody", "Short-tempered", "Impulsive")
            ZodiacSign.TAURUS -> listOf("Stubborn", "Possessive", "Uncompromising")
            ZodiacSign.GEMINI -> listOf("Nervous", "Inconsistent", "Indecisive")
            ZodiacSign.CANCER -> listOf("Moody", "Pessimistic", "Suspicious", "Manipulative")
            ZodiacSign.LEO -> listOf("Arrogant", "Stubborn", "Self-centered", "Lazy")
            ZodiacSign.VIRGO -> listOf("Shyness", "Worry", "Overly critical of self and others")
            ZodiacSign.LIBRA -> listOf("Indecisive", "Avoids confrontations", "Will carry a grudge")
            ZodiacSign.SCORPIO -> listOf("Distrusting", "Jealous", "Secretive", "Violent")
            ZodiacSign.SAGITTARIUS -> listOf("Promises more than can deliver", "Very impatient", "Will say anything no matter how undiplomatic")
            ZodiacSign.CAPRICORN -> listOf("Know-it-all", "Unforgiving", "Condescending", "Expecting the worst")
            ZodiacSign.AQUARIUS -> listOf("Runs from emotional expression", "Temperamental", "Uncompromising", "Aloof")
            ZodiacSign.PISCES -> listOf("Fearful", "Overly trusting", "Sad", "Desire to escape reality")
        }
    }

    fun getLuckyNumbers(sign: ZodiacSign): List<Int> {
        return when (sign) {
            ZodiacSign.ARIES -> listOf(1, 8, 17)
            ZodiacSign.TAURUS -> listOf(2, 6, 9, 12, 24)
            ZodiacSign.GEMINI -> listOf(5, 7, 14, 23)
            ZodiacSign.CANCER -> listOf(2, 3, 15, 20)
            ZodiacSign.LEO -> listOf(1, 3, 10, 19)
            ZodiacSign.VIRGO -> listOf(5, 14, 15, 23, 32)
            ZodiacSign.LIBRA -> listOf(4, 6, 13, 15, 24)
            ZodiacSign.SCORPIO -> listOf(8, 11, 18, 22)
            ZodiacSign.SAGITTARIUS -> listOf(3, 7, 9, 12, 21)
            ZodiacSign.CAPRICORN -> listOf(4, 8, 13, 22)
            ZodiacSign.AQUARIUS -> listOf(4, 7, 11, 22, 29)
            ZodiacSign.PISCES -> listOf(3, 9, 12, 15, 18, 24)
        }
    }

    fun getLuckyColor(sign: ZodiacSign): String {
        return when (sign) {
            ZodiacSign.ARIES -> "Red"
            ZodiacSign.TAURUS -> "Green, Pink"
            ZodiacSign.GEMINI -> "Light-Green, Yellow"
            ZodiacSign.CANCER -> "White"
            ZodiacSign.LEO -> "Gold, Yellow, Orange"
            ZodiacSign.VIRGO -> "Grey, Beige, Pale-Yellow"
            ZodiacSign.LIBRA -> "Pink, Green"
            ZodiacSign.SCORPIO -> "Scarlet, Red, Rust"
            ZodiacSign.SAGITTARIUS -> "Blue"
            ZodiacSign.CAPRICORN -> "Brown, Black"
            ZodiacSign.AQUARIUS -> "Light-Blue, Silver"
            ZodiacSign.PISCES -> "Mauve, Lilac, Purple, Violet, Sea green"
        }
    }
}
