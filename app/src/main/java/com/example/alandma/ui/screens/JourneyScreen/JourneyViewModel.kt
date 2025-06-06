// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/JourneyScreen/JourneyViewModel.kt
package com.example.alandma.ui.screens.JourneyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.local.entity.EventEntity
import com.example.alandma.data.repository.AlAndMaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JourneyViewModel @Inject constructor(
    private val repository: AlAndMaRepository
) : ViewModel() {

    private val _events = MutableStateFlow<List<EventEntity>>(emptyList())
    val events: StateFlow<List<EventEntity>> = _events

    init {
        viewModelScope.launch {
            repository.getAllEvents().collect { list ->
                _events.value = list
            }
        }
    }

    fun addOrUpdateEvent(entity: EventEntity) {
        viewModelScope.launch {
            repository.insertOrUpdateEvent(entity)
        }
    }

    fun deleteEvent(entity: EventEntity) {
        viewModelScope.launch {
            repository.deleteEvent(entity)
        }
    }
}
