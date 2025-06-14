// AlAndMa/app/src/main/java/com/example/alandma/data/remote/model/EventDto.kt
package com.example.alandma.data.remote.model

data class EventDto(
        val id: Long,
        val groupId: String,
        val createdBy: String,
        val updatedBy: String,
        val title: String,
        val description: String?,
        val eventDateMillis: Long,
        val type: String
)
