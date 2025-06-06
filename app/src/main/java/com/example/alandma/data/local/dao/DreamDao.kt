// AlAndMa/app/src/main/java/com/example/alandma/data/local/dao/DreamDao.kt
package com.example.alandma.data.local.dao

import androidx.room.*
import com.example.alandma.data.local.entity.DreamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDao {
    @Query("SELECT * FROM dreams ORDER BY targetDateMillis ASC")
    fun getAllDreams(): Flow<List<DreamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dream: DreamEntity): Long

    @Update
    suspend fun update(dream: DreamEntity)

    @Delete
    suspend fun delete(dream: DreamEntity)
}
