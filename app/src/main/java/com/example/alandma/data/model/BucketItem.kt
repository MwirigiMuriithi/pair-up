// AlAndMa/app/src/main/java/com/example/alandma/data/model/BucketItem.kt
package com.example.alandma.data.model

data class BucketItem(
    val id: Long = 0,
    val title: String,
    val category: String,
    val isCompleted: Boolean = false,
    val completedDateMillis: Long? = null,
    val isFavorite: Boolean = false
)
