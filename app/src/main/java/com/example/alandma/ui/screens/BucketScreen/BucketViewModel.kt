// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/BucketScreen/BucketViewModel.kt
package com.example.alandma.ui.screens.BucketScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.local.entity.BucketItemEntity
import com.example.alandma.data.repository.AlAndMaRepository
import com.example.alandma.util.DataStoreManager
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
//import javax.inject.Inject

//@HiltViewModel
class BucketViewModel (
    private val repository: AlAndMaRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    // raw flows from DataStore (nullable)
    private val rawUserId: Flow<String?>  = dataStore.userId
    private val rawGroupId: Flow<String?> = dataStore.groupId

    // map null → empty string (or you can throw / suspend if you prefer non-null)
    val currentUserId: StateFlow<String>  = rawUserId.map { it.orEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    val currentGroupId: StateFlow<String> = rawGroupId.map { it.orEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    private val _items = MutableStateFlow<List<BucketItemEntity>>(emptyList())
    val items: StateFlow<List<BucketItemEntity>> = _items

    init {
        viewModelScope.launch {
            repository.getAllBucketItems().collect { list ->
                _items.value = list
            }
        }
    }

    fun addOrUpdateItem(entity: BucketItemEntity) {
        viewModelScope.launch {
            repository.insertOrUpdateBucketItem(entity)
        }
    }

    fun deleteItem(entity: BucketItemEntity) {
        viewModelScope.launch {
            repository.deleteBucketItem(entity)
        }
    }

    fun toggleFavorite(entity: BucketItemEntity, isFav: Boolean) {
        viewModelScope.launch {
            val updated = entity.copy(isFavorite = isFav)
            repository.insertOrUpdateBucketItem(updated)
        }
    }

    fun toggleCompleted(entity: BucketItemEntity, isCompleted: Boolean) {
        viewModelScope.launch {
            val updated = entity.copy(
                isCompleted = isCompleted,
                completedDateMillis = if (isCompleted) System.currentTimeMillis() else null
            )
            repository.insertOrUpdateBucketItem(updated)
        }
    }
}
