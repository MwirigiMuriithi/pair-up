// AlAndMa/app/src/main/java/com/example/alandma/data/local/dao/BucketItemDao.kt
package com.example.alandma.data.local.dao

import androidx.room.*
import com.example.alandma.data.local.entity.BucketItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BucketItemDao {
    @Query("SELECT * FROM bucket_items ORDER BY isFavorite DESC, title ASC")
    fun getAllItems(): Flow<List<BucketItemEntity>>

    @Query("SELECT * FROM bucket_items WHERE category = :category ORDER BY isFavorite DESC")
    fun getItemsByCategory(category: String): Flow<List<BucketItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BucketItemEntity): Long

    @Update
    suspend fun update(item: BucketItemEntity)

    @Delete
    suspend fun delete(item: BucketItemEntity)
}
