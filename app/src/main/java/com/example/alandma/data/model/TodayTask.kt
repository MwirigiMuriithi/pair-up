// AlAndMa/app/src/main/java/com/example/alandma/data/model/TodayTask.kt
package com.example.alandma.data.model

data class TodayTask(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val dueDateMillis: Long
)
