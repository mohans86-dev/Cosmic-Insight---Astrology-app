package com.example.astrologyapp.ui.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.astrologyapp.data.model.BirthChart
import com.example.astrologyapp.data.repository.ChartRepository
import kotlinx.coroutines.launch

class ChartResultViewModel(private val repository: ChartRepository) : ViewModel() {

    fun saveChart(chart: BirthChart, onSaved: () -> Unit) {
        viewModelScope.launch {
            repository.saveChart(chart)
            onSaved()
        }
    }

    class Factory(private val repository: ChartRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChartResultViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ChartResultViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
