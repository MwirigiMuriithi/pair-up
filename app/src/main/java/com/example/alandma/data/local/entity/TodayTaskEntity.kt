// AlAndMa/app/src/main/java/com/example/alandma/data/local/entity/TodayTaskEntity.kt
package com.example.alandma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_tasks")
data class TodayTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val dueDateMillis: Long // store as epoch millis
)
