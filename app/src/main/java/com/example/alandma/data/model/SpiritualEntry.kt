// AlAndMa/app/src/main/java/com/example/alandma/data/model/SpiritualEntry.kt
package com.example.alandma.data.model

data class SpiritualEntry(
    val id: Long = 0,
    val title: String,
    val content: String,
    val timestampMillis: Long
)
