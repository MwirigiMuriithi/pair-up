// AlAndMa/app/src/main/java/com/example/alandma/data/remote/model/BucketItemDto.kt
package com.example.alandma.data.remote.model

data class BucketItemDto(
        val id: Long,
        val groupId: String,
        val createdBy: String,
        val updatedBy: String,
        val title: String,
        val category: String,
        val isCompleted: Boolean,
        val completedDateMillis: Long?,
        val isFavorite: Boolean
)
