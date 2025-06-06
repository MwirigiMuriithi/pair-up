// AlAndMa/app/src/main/java/com/example/alandma/data/remote/model/DreamDto.kt
package com.example.alandma.data.remote.model

data class DreamDto(
    val id: Long,
    val title: String,
    val description: String?,
    val isAchieved: Boolean,
    val targetDateMillis: Long
)
