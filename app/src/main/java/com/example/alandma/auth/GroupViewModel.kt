// app/src/main/java/com/example/alandma/auth/GroupViewModel.kt
package com.example.alandma.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.remote.repository.GroupRepository
import com.example.alandma.util.DataStoreManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class GroupUiState {
  object Idle : GroupUiState()
  object Loading : GroupUiState()
  data class Success(val groupId: String, val members: List<String>) : GroupUiState()
  data class Error(val msg: String) : GroupUiState()
}

class GroupViewModel(
  private val repo: GroupRepository,
  private val dataStore: DataStoreManager
) : ViewModel() {
  private val _ui = MutableStateFlow<GroupUiState>(GroupUiState.Idle)
  val ui: StateFlow<GroupUiState> = _ui.asStateFlow()

  fun createGroup(name: String, members: List<String>) = viewModelScope.launch {
    _ui.value = GroupUiState.Loading
    try {
      val dto = repo.createGroup(name, members)
      _ui.value = GroupUiState.Success(dto.id, dto.members)
    } catch(e: Exception) {
      _ui.value = GroupUiState.Error(e.localizedMessage ?: "Unknown error")
    }
  }

  fun loadExistingGroup() = viewModelScope.launch {
    dataStore.groupId.collect { id ->
      id?.let {
        _ui.value = GroupUiState.Loading
        try {
          val dto = repo.fetchGroup(it)
          _ui.value = GroupUiState.Success(dto.id, dto.members)
        } catch(e: Exception) {
          _ui.value = GroupUiState.Error(e.localizedMessage ?: "Fetch failed")
        }
      }
    }
  }
}
