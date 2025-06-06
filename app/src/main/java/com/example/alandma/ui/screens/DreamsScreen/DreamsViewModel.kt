// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/DreamsScreen/DreamsViewModel.kt
package com.example.alandma.ui.screens.DreamsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.local.entity.DreamEntity
import com.example.alandma.data.repository.AlAndMaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsViewModel @Inject constructor(
    private val repository: AlAndMaRepository
) : ViewModel() {

    private val _dreams = MutableStateFlow<List<DreamEntity>>(emptyList())
    val dreams: StateFlow<List<DreamEntity>> = _dreams

    init {
        viewModelScope.launch {
            repository.getAllDreams().collect { list ->
                _dreams.value = list
            }
        }
    }

    fun addOrUpdateDream(entity: DreamEntity) {
        viewModelScope.launch {
            repository.insertOrUpdateDream(entity)
        }
    }

    fun deleteDream(entity: DreamEntity) {
        viewModelScope.launch {
            repository.deleteDream(entity)
        }
    }

    fun toggleAchieved(entity: DreamEntity, achieved: Boolean) {
        viewModelScope.launch {
            val updated = entity.copy(isAchieved = achieved)
            repository.insertOrUpdateDream(updated)
        }
    }
}
