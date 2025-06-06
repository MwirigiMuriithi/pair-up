// AlAndMa/app/src/main/java/com/example/alandma/data/repository/LocalRepository.kt
package com.example.alandma.data.repository

import com.example.alandma.data.local.dao.*
import com.example.alandma.data.local.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val todayDao: TodayDao,
    private val eventDao: EventDao,
    private val bucketItemDao: BucketItemDao,
    private val spiritualEntryDao: SpiritualEntryDao,
    private val dreamDao: DreamDao
) {

    // TodayTask
    fun getAllTodayTasks(): Flow<List<TodayTaskEntity>> = todayDao.getAllTasks()
    suspend fun insertTodayTask(entity: TodayTaskEntity) = todayDao.insert(entity)
    suspend fun updateTodayTask(entity: TodayTaskEntity) = todayDao.update(entity)
    suspend fun deleteTodayTask(entity: TodayTaskEntity) = todayDao.delete(entity)

    // Event
    fun getAllEvents(): Flow<List<EventEntity>> = eventDao.getAllEvents()
    fun getEventsByType(type: String) = eventDao.getEventsByType(type)
    suspend fun insertEvent(entity: EventEntity) = eventDao.insert(entity)
    suspend fun updateEvent(entity: EventEntity) = eventDao.update(entity)
    suspend fun deleteEvent(entity: EventEntity) = eventDao.delete(entity)

    // BucketItem
    fun getAllBucketItems(): Flow<List<BucketItemEntity>> = bucketItemDao.getAllItems()
    fun getBucketItemsByCategory(category: String) = bucketItemDao.getItemsByCategory(category)
    suspend fun insertBucketItem(entity: BucketItemEntity) = bucketItemDao.insert(entity)
    suspend fun updateBucketItem(entity: BucketItemEntity) = bucketItemDao.update(entity)
    suspend fun deleteBucketItem(entity: BucketItemEntity) = bucketItemDao.delete(entity)

    // SpiritualEntry
    fun getAllSpiritualEntries(): Flow<List<SpiritualEntryEntity>> = spiritualEntryDao.getAllEntries()
    suspend fun insertSpiritualEntry(entity: SpiritualEntryEntity) = spiritualEntryDao.insert(entity)
    suspend fun updateSpiritualEntry(entity: SpiritualEntryEntity) = spiritualEntryDao.update(entity)
    suspend fun deleteSpiritualEntry(entity: SpiritualEntryEntity) = spiritualEntryDao.delete(entity)

    // Dream
    fun getAllDreams(): Flow<List<DreamEntity>> = dreamDao.getAllDreams()
    suspend fun insertDream(entity: DreamEntity) = dreamDao.insert(entity)
    suspend fun updateDream(entity: DreamEntity) = dreamDao.update(entity)
    suspend fun deleteDream(entity: DreamEntity) = dreamDao.delete(entity)
}
