//// AlAndMa/app/src/main/java/com/example/alandma/di/AppModule.kt

package com.example.alandma.di

import android.content.Context
import androidx.room.Room
import com.example.alandma.data.local.db.AlAndMaDatabase
import com.example.alandma.data.remote.api.AuthService
import com.example.alandma.data.remote.api.AlAndMaService
import com.example.alandma.data.remote.repository.AuthRepository
import com.example.alandma.data.remote.repository.RemoteRepository
import com.example.alandma.data.repository.LocalRepository
import com.example.alandma.data.repository.AlAndMaRepository
import com.example.alandma.auth.AuthViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

  // --- HTTP client w/ logging interceptor ---
  single {
    OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
      .build()
  }

  // --- Retrofit instances ---
  single {
    Retrofit.Builder()
      .baseUrl("https://your-nestjs-server.com/api/")
      .client(get())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  single { get<Retrofit>().create(AuthService::class.java) }
  single { get<Retrofit>().create(AlAndMaService::class.java) }

  // --- Auth Repository & ViewModel ---
  single { AuthRepository(get()) }
  viewModel { AuthViewModel(get()) }

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

  // --- Data + Repo Layer ---
  single { RemoteRepository(get()) }
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

  // --- Auth ViewModel ---
  factory { com.example.alandma.auth.AuthViewModel(get()) }
  factory { com.example.alandma.auth.GroupViewModel(get(), get()) }
  // --- Feature ViewModels ---
  viewModel { com.example.alandma.ui.screens.TodayScreen.TodayViewModel(get()) }
  viewModel { com.example.alandma.ui.screens.JourneyScreen.JourneyViewModel(get()) }
  viewModel { com.example.alandma.ui.screens.BucketScreen.BucketViewModel(get()) }
  viewModel { com.example.alandma.ui.screens.HeartsScreen.HeartsViewModel(get()) }
  viewModel { com.example.alandma.ui.screens.DreamsScreen.DreamsViewModel(get()) }
}



// package com.example.alandma.di

// import android.content.Context
// import androidx.room.Room
// import com.example.alandma.data.local.db.AlAndMaDatabase
// import com.example.alandma.data.remote.repository.RemoteRepository
// import com.example.alandma.data.repository.LocalRepository
// import com.example.alandma.data.repository.AlAndMaRepository
// import org.koin.android.ext.koin.androidContext
// import org.koin.androidx.viewmodel.dsl.viewModel
// import org.koin.dsl.module

// val appModule = module {

//     // --- ROOM DATABASE + DAOs ---
//     single {
//         Room.databaseBuilder(
//             androidContext(),
//             AlAndMaDatabase::class.java,
//             "alandma_db"
//         ).build()
//     }
//     single { get<AlAndMaDatabase>().todayDao() }
//     single { get<AlAndMaDatabase>().eventDao() }
//     single { get<AlAndMaDatabase>().bucketItemDao() }
//     single { get<AlAndMaDatabase>().spiritualEntryDao() }
//     single { get<AlAndMaDatabase>().dreamDao() }

//     // --- REPOSITORIES ---
//     single { RemoteRepository() }
//     single {
//         LocalRepository(
//             todayDao          = get(),
//             eventDao          = get(),
//             bucketItemDao     = get(),
//             spiritualEntryDao = get(),
//             dreamDao          = get()
//         )
//     }
//     single { AlAndMaRepository(get(), get()) }

//     // --- VIEWMODELS ---
//     viewModel { com.example.alandma.ui.screens.TodayScreen.TodayViewModel(get()) }
//     viewModel { com.example.alandma.ui.screens.JourneyScreen.JourneyViewModel(get()) }
//     viewModel { com.example.alandma.ui.screens.BucketScreen.BucketViewModel(get()) }
//     viewModel { com.example.alandma.ui.screens.HeartsScreen.HeartsViewModel(get()) }
//     viewModel { com.example.alandma.ui.screens.DreamsScreen.DreamsViewModel(get()) }
// }

