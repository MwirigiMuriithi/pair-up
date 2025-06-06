// AlAndMa/app/src/main/java/com/example/alandma/data/model/Dream.kt
package com.example.alandma.data.model

data class Dream(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isAchieved: Boolean = false,
    val targetDateMillis: Long
)
