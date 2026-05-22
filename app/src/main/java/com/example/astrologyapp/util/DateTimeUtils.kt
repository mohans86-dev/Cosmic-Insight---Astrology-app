package com.example.astrologyapp.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DateTimeUtils {
    private val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    private val timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)

    fun formatDate(date: LocalDate): String = date.format(dateFormatter)
    
    fun formatTime(time: LocalTime): String = time.format(timeFormatter)
    
    fun parseDate(dateStr: String): LocalDate? {
        return try {
            LocalDate.parse(dateStr, dateFormatter)
        } catch (e: Exception) {
            null
        }
    }
}
