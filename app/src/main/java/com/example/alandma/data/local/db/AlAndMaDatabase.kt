// AlAndMa/app/src/main/java/com/example/alandma/data/local/db/AlAndMaDatabase.kt
package com.example.alandma.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alandma.data.local.dao.*
import com.example.alandma.data.local.entity.*

@Database(
    entities = [
        TodayTaskEntity::class,
        EventEntity::class,
        BucketItemEntity::class,
        SpiritualEntryEntity::class,
        DreamEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AlAndMaDatabase : RoomDatabase() {
    abstract fun todayDao(): TodayDao
    abstract fun eventDao(): EventDao
    abstract fun bucketItemDao(): BucketItemDao
    abstract fun spiritualEntryDao(): SpiritualEntryDao
    abstract fun dreamDao(): DreamDao
}
