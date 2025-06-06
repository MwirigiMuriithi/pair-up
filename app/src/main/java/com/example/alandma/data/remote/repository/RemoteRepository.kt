// AlAndMa/app/src/main/java/com/example/alandma/data/remote/repository/RemoteRepository.kt
package com.example.alandma.data.remote.repository

import com.example.alandma.data.remote.api.RetrofitInstance
import com.example.alandma.data.remote.model.*
import retrofit2.Response

class RemoteRepository {
    private val api = RetrofitInstance.api

    // TodayTask
    suspend fun fetchAllTodayTasks(): Response<List<TodayTaskDto>> = api.getAllTodayTasks()
    suspend fun pushTodayTask(dto: TodayTaskDto): Response<TodayTaskDto> = api.createTodayTask(dto)
    suspend fun updateTodayTask(dto: TodayTaskDto): Response<TodayTaskDto> = api.updateTodayTask(dto.id, dto)
    suspend fun deleteTodayTask(id: Long): Response<Unit> = api.deleteTodayTask(id)

    // Event
    suspend fun fetchAllEvents(): Response<List<EventDto>> = api.getAllEvents()
    suspend fun pushEvent(dto: EventDto): Response<EventDto> = api.createEvent(dto)
    suspend fun updateEvent(dto: EventDto): Response<EventDto> = api.updateEvent(dto.id, dto)
    suspend fun deleteEvent(id: Long): Response<Unit> = api.deleteEvent(id)

    // BucketItem
    suspend fun fetchAllBucketItems(): Response<List<BucketItemDto>> = api.getAllBucketItems()
    suspend fun pushBucketItem(dto: BucketItemDto): Response<BucketItemDto> = api.createBucketItem(dto)
    suspend fun updateBucketItem(dto: BucketItemDto): Response<BucketItemDto> = api.updateBucketItem(dto.id, dto)
    suspend fun deleteBucketItem(id: Long): Response<Unit> = api.deleteBucketItem(id)

    // SpiritualEntry
    suspend fun fetchAllSpiritualEntries(): Response<List<SpiritualEntryDto>> = api.getAllSpiritualEntries()
    suspend fun pushSpiritualEntry(dto: SpiritualEntryDto): Response<SpiritualEntryDto> = api.createSpiritualEntry(dto)
    suspend fun updateSpiritualEntry(dto: SpiritualEntryDto): Response<SpiritualEntryDto> = api.updateSpiritualEntry(dto.id, dto)
    suspend fun deleteSpiritualEntry(id: Long): Response<Unit> = api.deleteSpiritualEntry(id)

    // Dream
    suspend fun fetchAllDreams(): Response<List<DreamDto>> = api.getAllDreams()
    suspend fun pushDream(dto: DreamDto): Response<DreamDto> = api.createDream(dto)
    suspend fun updateDream(dto: DreamDto): Response<DreamDto> = api.updateDream(dto.id, dto)
    suspend fun deleteDream(id: Long): Response<Unit> = api.deleteDream(id)
}
