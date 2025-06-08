// AlAndMa/app/src/main/java/com/example/alandma/data/local/dao/TodayDao.kt
package com.example.alandma.data.local.dao

import androidx.room.*
import com.example.alandma.data.local.entity.TodayTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodayDao {
    @Query("SELECT * FROM today_tasks ORDER BY dueDateMillis ASC")
    fun getAllTasks(): Flow<List<TodayTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TodayTaskEntity): Long

    @Update
    suspend fun update(task: TodayTaskEntity)

    @Delete
    suspend fun delete(task: TodayTaskEntity)

    @Query("DELETE FROM today_tasks")
    suspend fun clearAll()
}

// @Dao
// interface TodayDao {
//   @Query("SELECT * FROM today_tasks WHERE groupId = :groupId ORDER BY dueDateMillis ASC")
//   fun getAllTasks(groupId: String): Flow<List<TodayTaskEntity>>

//   @Insert(onConflict = OnConflictStrategy.REPLACE)
//   suspend fun insert(task: TodayTaskEntity): Long

//   @Update suspend fun update(task: TodayTaskEntity)
//   @Delete suspend fun delete(task: TodayTaskEntity)
// }
