// AlAndMa/app/src/main/java/com/example/alandma/data/local/entity/SpiritualEntryEntity.kt
package com.example.alandma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spiritual_entries")
data class SpiritualEntryEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val groupId: String,
        val createdBy: String,
        val updatedBy: String,
        val title: String, // e.g. “Morning Prayer” or “John 3:16”
        val content: String, // prayer text or reflection
        val timestampMillis: Long // when it was created/added
)
