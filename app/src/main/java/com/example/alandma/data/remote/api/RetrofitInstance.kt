// AlAndMa/app/src/main/java/com/example/alandma/data/remote/api/RetrofitInstance.kt
package com.example.alandma.data.remote.api

import com.example.alandma.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: AlAndMaService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // point to your hosted server
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlAndMaService::class.java)
    }
}
