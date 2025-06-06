// AlAndMa/app/src/main/java/com/example/alandma/data/model/Event.kt
package com.example.alandma.data.model

data class Event(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val eventDateMillis: Long,
    val type: String
)
