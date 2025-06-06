// AlAndMa/app/src/main/java/com/example/alandma/data/local/dao/EventDao.kt
package com.example.alandma.data.local.dao

import androidx.room.*
import com.example.alandma.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events ORDER BY eventDateMillis ASC")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE type = :filterType ORDER BY eventDateMillis ASC")
    fun getEventsByType(filterType: String): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity): Long

    @Update
    suspend fun update(event: EventEntity)

    @Delete
    suspend fun delete(event: EventEntity)
}
