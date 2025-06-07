// AlAndMa/app/src/main/java/com/example/alandma/data/repository/AlAndMaRepository.kt
package com.example.alandma.data.repository

import com.example.alandma.data.local.entity.*
import com.example.alandma.data.remote.model.*
import com.example.alandma.data.remote.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//import javax.inject.Singleton

/**
 * Combines local Room operations with remote sync calls.
 * For brevity, mapping between Entity <-> Model/Dto is done inline.
 */
class AlAndMaRepository (
    private val local: LocalRepository,
    private val remote: RemoteRepository
) {
    // === Today Tasks ===
    fun getAllTodayTasks(): Flow<List<TodayTaskEntity>> = local.getAllTodayTasks()

    suspend fun insertOrUpdateTodayTask(entity: TodayTaskEntity) {
        val id = local.insertTodayTask(entity)
        val dto = TodayTaskDto(
            id = if (entity.id != 0L) entity.id else id,
            title = entity.title,
            description = entity.description,
            isCompleted = entity.isCompleted,
            dueDateMillis = entity.dueDateMillis
        )
        remote.pushTodayTask(dto) // ignore result for now
    }

    suspend fun deleteTodayTask(entity: TodayTaskEntity) {
        local.deleteTodayTask(entity)
        remote.deleteTodayTask(entity.id)
    }

    // === Events ===
    fun getAllEvents(): Flow<List<EventEntity>> = local.getAllEvents()

    suspend fun insertOrUpdateEvent(entity: EventEntity) {
        val id = local.insertEvent(entity)
        val dto = EventDto(
            id = if (entity.id != 0L) entity.id else id,
            title = entity.title,
            description = entity.description,
            eventDateMillis = entity.eventDateMillis,
            type = entity.type
        )
        remote.pushEvent(dto)
    }

    suspend fun deleteEvent(entity: EventEntity) {
        local.deleteEvent(entity)
        remote.deleteEvent(entity.id)
    }

    // === Bucket Items ===
    fun getAllBucketItems(): Flow<List<BucketItemEntity>> = local.getAllBucketItems()

    suspend fun insertOrUpdateBucketItem(entity: BucketItemEntity) {
        val id = local.insertBucketItem(entity)
        val dto = BucketItemDto(
            id = if (entity.id != 0L) entity.id else id,
            title = entity.title,
            category = entity.category,
            isCompleted = entity.isCompleted,
            completedDateMillis = entity.completedDateMillis,
            isFavorite = entity.isFavorite
        )
        remote.pushBucketItem(dto)
    }

    suspend fun deleteBucketItem(entity: BucketItemEntity) {
        local.deleteBucketItem(entity)
        remote.deleteBucketItem(entity.id)
    }

    // === Spiritual Entries ===
    fun getAllSpiritualEntries(): Flow<List<SpiritualEntryEntity>> = local.getAllSpiritualEntries()

    suspend fun insertOrUpdateSpiritualEntry(entity: SpiritualEntryEntity) {
        val id = local.insertSpiritualEntry(entity)
        val dto = SpiritualEntryDto(
            id = if (entity.id != 0L) entity.id else id,
            title = entity.title,
            content = entity.content,
            timestampMillis = entity.timestampMillis
        )
        remote.pushSpiritualEntry(dto)
    }

    suspend fun deleteSpiritualEntry(entity: SpiritualEntryEntity) {
        local.deleteSpiritualEntry(entity)
        remote.deleteSpiritualEntry(entity.id)
    }

    // === Dreams ===
    fun getAllDreams(): Flow<List<DreamEntity>> = local.getAllDreams()

    suspend fun insertOrUpdateDream(entity: DreamEntity) {
        val id = local.insertDream(entity)
        val dto = DreamDto(
            id = if (entity.id != 0L) entity.id else id,
            title = entity.title,
            description = entity.description,
            isAchieved = entity.isAchieved,
            targetDateMillis = entity.targetDateMillis
        )
        remote.pushDream(dto)
    }

    suspend fun deleteDream(entity: DreamEntity) {
        local.deleteDream(entity)
        remote.deleteDream(entity.id)
    }
}
