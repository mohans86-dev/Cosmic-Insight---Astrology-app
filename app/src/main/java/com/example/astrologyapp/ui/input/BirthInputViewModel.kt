package com.example.astrologyapp.ui.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astrologyapp.data.local.AstrologyCalculator
import com.example.astrologyapp.data.model.BirthChart
import com.example.astrologyapp.data.model.BirthDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BirthInputViewModel(private val calculator: AstrologyCalculator) : ViewModel() {

    suspend fun generateChart(details: BirthDetails): BirthChart {
        return withContext(Dispatchers.Default) {
            calculator.calculateBirthChart(details)
        }
    }

    class Factory(private val calculator: AstrologyCalculator) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BirthInputViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BirthInputViewModel(calculator) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
