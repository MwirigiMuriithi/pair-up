// AlAndMa/app/src/main/java/com/example/alandma/data/remote/model/TodayTaskDto.kt
package com.example.alandma.data.remote.model

data class TodayTaskDto(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val dueDateMillis: Long
)
