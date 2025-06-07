//// AlAndMa/app/src/main/java/com/example/alandma/di/AppModule.kt
package com.example.alandma.di

import android.content.Context
import androidx.room.Room
import com.example.alandma.data.local.db.AlAndMaDatabase
import com.example.alandma.data.remote.repository.RemoteRepository
import com.example.alandma.data.repository.LocalRepository
import com.example.alandma.data.repository.AlAndMaRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // --- ROOM DATABASE + DAOs ---
    single {
        Room.databaseBuilder(
            androidContext(),
            AlAndMaDatabase::class.java,
            "alandma_db"
        ).build()
    }
    single { get<AlAndMaDatabase>().todayDao() }
    single { get<AlAndMaDatabase>().eventDao() }
    single { get<AlAndMaDatabase>().bucketItemDao() }
    single { get<AlAndMaDatabase>().spiritualEntryDao() }
    single { get<AlAndMaDatabase>().dreamDao() }

    // --- REPOSITORIES ---
    single { RemoteRepository() }
    single {
        LocalRepository(
            todayDao          = get(),
            eventDao          = get(),
            bucketItemDao     = get(),
            spiritualEntryDao = get(),
            dreamDao          = get()
        )
    }
    single { AlAndMaRepository(get(), get()) }

    // --- VIEWMODELS ---
    viewModel { com.example.alandma.ui.screens.TodayScreen.TodayViewModel(get()) }
    viewModel { com.example.alandma.ui.screens.JourneyScreen.JourneyViewModel(get()) }
    viewModel { com.example.alandma.ui.screens.BucketScreen.BucketViewModel(get()) }
    viewModel { com.example.alandma.ui.screens.HeartsScreen.HeartsViewModel(get()) }
    viewModel { com.example.alandma.ui.screens.DreamsScreen.DreamsViewModel(get()) }
}

//// AlAndMa/app/src/main/java/com/example/alandma/di/AppModule.kt
//package com.example.alandma.di
//
//import com.example.alandma.data.remote.repository.RemoteRepository
//import com.example.alandma.data.repository.LocalRepository
//import com.example.alandma.data.repository.AlAndMaRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ApplicationComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(ApplicationComponent::class)
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun provideRemoteRepository(): RemoteRepository = RemoteRepository()
//
//    @Singleton
//    @Provides
//    fun provideLocalRepository(
//        todayDao: com.example.alandma.data.local.dao.TodayDao,
//        eventDao: com.example.alandma.data.local.dao.EventDao,
//        bucketItemDao: com.example.alandma.data.local.dao.BucketItemDao,
//        spiritualEntryDao: com.example.alandma.data.local.dao.SpiritualEntryDao,
//        dreamDao: com.example.alandma.data.local.dao.DreamDao
//    ): LocalRepository {
//        return LocalRepository(
//            todayDao = todayDao,
//            eventDao = eventDao,
//            bucketItemDao = bucketItemDao,
//            spiritualEntryDao = spiritualEntryDao,
//            dreamDao = dreamDao
//        )
//    }
//
//    @Singleton
//    @Provides
//    fun provideAlAndMaRepository(
//        local: LocalRepository,
//        remote: RemoteRepository
//    ): AlAndMaRepository {
//        return AlAndMaRepository(local, remote)
//    }
//}
