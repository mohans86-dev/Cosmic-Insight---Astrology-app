package com.example.astrologyapp.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.astrologyapp.data.local.ChartEntity
import com.example.astrologyapp.data.repository.ChartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedChartsViewModel(private val repository: ChartRepository) : ViewModel() {

    val savedCharts: StateFlow<List<ChartEntity>> = repository.getSavedCharts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteChart(id: Long) {
        viewModelScope.launch {
            repository.deleteChart(id)
        }
    }

    class Factory(private val repository: ChartRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SavedChartsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SavedChartsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
