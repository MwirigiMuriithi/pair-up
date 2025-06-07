// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/HeartsScreen/HeartsViewModel.kt
package com.example.alandma.ui.screens.HeartsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.local.entity.SpiritualEntryEntity
import com.example.alandma.data.repository.AlAndMaRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
//import javax.inject.Inject

//@HiltViewModel
class HeartsViewModel(
    private val repository: AlAndMaRepository
) : ViewModel() {

    private val _entries = MutableStateFlow<List<SpiritualEntryEntity>>(emptyList())
    val entries: StateFlow<List<SpiritualEntryEntity>> = _entries

    init {
        viewModelScope.launch {
            repository.getAllSpiritualEntries().collect { list ->
                _entries.value = list
            }
        }
    }

    fun addOrUpdateEntry(entity: SpiritualEntryEntity) {
        viewModelScope.launch {
            repository.insertOrUpdateSpiritualEntry(entity)
        }
    }

    fun deleteEntry(entity: SpiritualEntryEntity) {
        viewModelScope.launch {
            repository.deleteSpiritualEntry(entity)
        }
    }
}
