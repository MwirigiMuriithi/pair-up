// AlAndMa/app/src/main/java/com/example/alandma/data/local/entity/DreamEntity.kt
package com.example.alandma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dreams")
data class DreamEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        // TODO: pass in values once I wire in authentication, DO NOT USE DEFAULTS.
        val groupId: String = "",
        val createdBy: String = "",
        val updatedBy: String = "",
        val title: String, // e.g. “Move to a new city”
        val description: String?, // extra notes
        val isAchieved: Boolean = false,
        val targetDateMillis: Long // when you hope to achieve it
)
