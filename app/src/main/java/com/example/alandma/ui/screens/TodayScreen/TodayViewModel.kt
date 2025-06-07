// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/TodayScreen/TodayViewModel.kt
package com.example.alandma.ui.screens.TodayScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.local.entity.TodayTaskEntity
import com.example.alandma.data.repository.AlAndMaRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
//import javax.inject.Inject

//@HiltViewModel
class TodayViewModel(
    private val repository: AlAndMaRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TodayTaskEntity>>(emptyList())
    val tasks: StateFlow<List<TodayTaskEntity>> = _tasks

    init {
        viewModelScope.launch {
            repository.getAllTodayTasks().collect { entities ->
                _tasks.value = entities
            }
        }
    }

    fun addOrUpdateTask(entity: TodayTaskEntity) {
        viewModelScope.launch {
            repository.insertOrUpdateTodayTask(entity)
        }
    }

    fun deleteTask(entity: TodayTaskEntity) {
        viewModelScope.launch {
            repository.deleteTodayTask(entity)
        }
    }

    fun toggleComplete(entity: TodayTaskEntity, isChecked: Boolean) {
        viewModelScope.launch {
            val updated = entity.copy(isCompleted = isChecked)
            repository.insertOrUpdateTodayTask(updated)
        }
    }
}
