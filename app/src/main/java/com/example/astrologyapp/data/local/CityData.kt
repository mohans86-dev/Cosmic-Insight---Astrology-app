package com.example.astrologyapp.data.local

data class CityInfo(val name: String, val state: String?, val country: String, val latitude: Double, val longitude: Double, val timezone: String) {
    val displayName: String
        get() = if (state != null) "$name, $state, $country" else "$name, $country"
}

object CityData {
    val cities = listOf(
        CityInfo("Mumbai", "Maharashtra", "India", 19.0760, 72.8777, "Asia/Kolkata"),
        CityInfo("Delhi", "Delhi", "India", 28.7041, 77.1025, "Asia/Kolkata"),
        CityInfo("Bangalore", "Karnataka", "India", 12.9716, 77.5946, "Asia/Kolkata"),
        CityInfo("Hyderabad", "Telangana", "India", 17.3850, 78.4867, "Asia/Kolkata"),
        CityInfo("Chennai", "Tamil Nadu", "India", 13.0827, 80.2707, "Asia/Kolkata"),
        CityInfo("Kolkata", "West Bengal", "India", 22.5726, 88.3639, "Asia/Kolkata"),
        CityInfo("Pune", "Maharashtra", "India", 18.5204, 73.8567, "Asia/Kolkata"),
        CityInfo("Ahmedabad", "Gujarat", "India", 23.0225, 72.5714, "Asia/Kolkata"),
        CityInfo("Jaipur", "Rajasthan", "India", 26.9124, 75.7873, "Asia/Kolkata"),
        CityInfo("Lucknow", "Uttar Pradesh", "India", 26.8467, 80.9462, "Asia/Kolkata"),
        CityInfo("New York", "New York", "USA", 40.7128, -74.0060, "America/New_York"),
        CityInfo("Los Angeles", "California", "USA", 34.0522, -118.2437, "America/Los_Angeles"),
        CityInfo("London", null, "UK", 51.5074, -0.1278, "Europe/London"),
        CityInfo("Paris", null, "France", 48.8566, 2.3522, "Europe/Paris"),
        CityInfo("Tokyo", null, "Japan", 35.6762, 139.6503, "Asia/Tokyo"),
        CityInfo("Sydney", "New South Wales", "Australia", -33.8688, 151.2093, "Australia/Sydney"),
        CityInfo("Dubai", null, "UAE", 25.2048, 55.2708, "Asia/Dubai"),
        CityInfo("Toronto", "Ontario", "Canada", 43.6510, -79.3470, "America/Toronto"),
        CityInfo("Singapore", null, "Singapore", 1.3521, 103.8198, "Asia/Singapore"),
        CityInfo("Cape Town", "Western Cape", "South Africa", -33.9249, 18.4241, "Africa/Johannesburg")
    )

    fun searchCities(query: String): List<CityInfo> {
        if (query.isBlank()) return emptyList()
        val lowerQuery = query.lowercase()
        return cities.filter {
            it.name.lowercase().contains(lowerQuery) || 
            it.country.lowercase().contains(lowerQuery) ||
            (it.state?.lowercase()?.contains(lowerQuery) == true)
        }.take(20)
    }
}
