// AlAndMa/app/src/main/java/com/example/alandma/di/AppModule.kt
package com.example.alandma.di

import com.example.alandma.data.remote.repository.RemoteRepository
import com.example.alandma.data.repository.LocalRepository
import com.example.alandma.data.repository.AlAndMaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(): RemoteRepository = RemoteRepository()

    @Singleton
    @Provides
    fun provideLocalRepository(
        todayDao: com.example.alandma.data.local.dao.TodayDao,
        eventDao: com.example.alandma.data.local.dao.EventDao,
        bucketItemDao: com.example.alandma.data.local.dao.BucketItemDao,
        spiritualEntryDao: com.example.alandma.data.local.dao.SpiritualEntryDao,
        dreamDao: com.example.alandma.data.local.dao.DreamDao
    ): LocalRepository {
        return LocalRepository(
            todayDao = todayDao,
            eventDao = eventDao,
            bucketItemDao = bucketItemDao,
            spiritualEntryDao = spiritualEntryDao,
            dreamDao = dreamDao
        )
    }

    @Singleton
    @Provides
    fun provideAlAndMaRepository(
        local: LocalRepository,
        remote: RemoteRepository
    ): AlAndMaRepository {
        return AlAndMaRepository(local, remote)
    }
}
