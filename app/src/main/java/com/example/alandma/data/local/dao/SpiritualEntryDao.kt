// AlAndMa/app/src/main/java/com/example/alandma/data/local/dao/SpiritualEntryDao.kt
package com.example.alandma.data.local.dao

import androidx.room.*
import com.example.alandma.data.local.entity.SpiritualEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpiritualEntryDao {
    @Query("SELECT * FROM spiritual_entries ORDER BY timestampMillis DESC")
    fun getAllEntries(): Flow<List<SpiritualEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: SpiritualEntryEntity): Long

    @Update
    suspend fun update(entry: SpiritualEntryEntity)

    @Delete
    suspend fun delete(entry: SpiritualEntryEntity)
}
