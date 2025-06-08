// AlAndMa/app/src/main/java/com/example/alandma/data/remote/model/DreamDto.kt
package com.example.alandma.data.remote.model

data class DreamDto(
        val id: Long,
        val groupId: String,
        val createdBy: String,
        val updatedBy: String,
        val title: String,
        val description: String?,
        val isAchieved: Boolean,
        val targetDateMillis: Long
)
