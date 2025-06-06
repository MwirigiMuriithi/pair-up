// AlAndMa/app/src/main/java/com/example/alandma/di/DatabaseModule.kt
package com.example.alandma.di

import android.content.Context
import androidx.room.Room
import com.example.alandma.data.local.dao.*
import com.example.alandma.data.local.db.AlAndMaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AlAndMaDatabase {
        return Room.databaseBuilder(
            context,
            AlAndMaDatabase::class.java,
            "alandma_db"
        ).build()
    }

    @Provides
    fun provideTodayDao(db: AlAndMaDatabase): TodayDao = db.todayDao()

    @Provides
    fun provideEventDao(db: AlAndMaDatabase): EventDao = db.eventDao()

    @Provides
    fun provideBucketItemDao(db: AlAndMaDatabase): BucketItemDao = db.bucketItemDao()

    @Provides
    fun provideSpiritualEntryDao(db: AlAndMaDatabase): SpiritualEntryDao = db.spiritualEntryDao()

    @Provides
    fun provideDreamDao(db: AlAndMaDatabase): DreamDao = db.dreamDao()
}
