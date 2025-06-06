// AlAndMa/app/src/main/java/com/example/alandma/data/remote/api/AlAndMaService.kt
package com.example.alandma.data.remote.api

import com.example.alandma.data.remote.model.*
import retrofit2.Response
import retrofit2.http.*

interface AlAndMaService {
    // --- TodayTask ---
    @GET("todayTasks")
    suspend fun getAllTodayTasks(): Response<List<TodayTaskDto>>

    @POST("todayTasks")
    suspend fun createTodayTask(@Body task: TodayTaskDto): Response<TodayTaskDto>

    @PUT("todayTasks/{id}")
    suspend fun updateTodayTask(@Path("id") id: Long, @Body task: TodayTaskDto): Response<TodayTaskDto>

    @DELETE("todayTasks/{id}")
    suspend fun deleteTodayTask(@Path("id") id: Long): Response<Unit>

    // --- Event ---
    @GET("events")
    suspend fun getAllEvents(): Response<List<EventDto>>

    @POST("events")
    suspend fun createEvent(@Body event: EventDto): Response<EventDto>

    @PUT("events/{id}")
    suspend fun updateEvent(@Path("id") id: Long, @Body event: EventDto): Response<EventDto>

    @DELETE("events/{id}")
    suspend fun deleteEvent(@Path("id") id: Long): Response<Unit>

    // --- BucketItem ---
    @GET("bucketItems")
    suspend fun getAllBucketItems(): Response<List<BucketItemDto>>

    @POST("bucketItems")
    suspend fun createBucketItem(@Body item: BucketItemDto): Response<BucketItemDto>

    @PUT("bucketItems/{id}")
    suspend fun updateBucketItem(@Path("id") id: Long, @Body item: BucketItemDto): Response<BucketItemDto>

    @DELETE("bucketItems/{id}")
    suspend fun deleteBucketItem(@Path("id") id: Long): Response<Unit>

    // --- SpiritualEntry ---
    @GET("spiritualEntries")
    suspend fun getAllSpiritualEntries(): Response<List<SpiritualEntryDto>>

    @POST("spiritualEntries")
    suspend fun createSpiritualEntry(@Body entry: SpiritualEntryDto): Response<SpiritualEntryDto>

    @PUT("spiritualEntries/{id}")
    suspend fun updateSpiritualEntry(@Path("id") id: Long, @Body entry: SpiritualEntryDto): Response<SpiritualEntryDto>

    @DELETE("spiritualEntries/{id}")
    suspend fun deleteSpiritualEntry(@Path("id") id: Long): Response<Unit>

    // --- Dream ---
    @GET("dreams")
    suspend fun getAllDreams(): Response<List<DreamDto>>

    @POST("dreams")
    suspend fun createDream(@Body dream: DreamDto): Response<DreamDto>

    @PUT("dreams/{id}")
    suspend fun updateDream(@Path("id") id: Long, @Body dream: DreamDto): Response<DreamDto>

    @DELETE("dreams/{id}")
    suspend fun deleteDream(@Path("id") id: Long): Response<Unit>
}
