// AlAndMa/app/src/main/java/com/example/alandma/data/local/entity/BucketItemEntity.kt
package com.example.alandma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bucket_items")
data class BucketItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val category: String, // "Travel", "Food", etc.
    val isCompleted: Boolean = false,
    val completedDateMillis: Long? = null,
    val isFavorite: Boolean = false
)
