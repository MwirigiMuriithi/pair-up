// AlAndMa/app/src/main/java/com/example/alandma/data/local/entity/EventEntity.kt
package com.example.alandma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val groupId: String,
        val createdBy: String,
        val updatedBy: String,
        val title: String,
        val description: String? = null,
        val eventDateMillis: Long, // epoch millis for calendar
        val type: String // e.g. "spiritual", "goal", "general", etc.
)
