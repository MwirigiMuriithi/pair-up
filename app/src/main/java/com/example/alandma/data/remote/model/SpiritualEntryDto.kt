// AlAndMa/app/src/main/java/com/example/alandma/data/remote/model/SpiritualEntryDto.kt
package com.example.alandma.data.remote.model

data class SpiritualEntryDto(
        val id: Long,
        val groupId: String,
        val createdBy: String,
        val updatedBy: String,
        val title: String,
        val content: String,
        val timestampMillis: Long
)
