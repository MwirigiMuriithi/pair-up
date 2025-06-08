// app/src/main/java/com/example/alandma/data/remote/api/AuthService.kt
package com.example.alandma.data.remote.api

import com.example.alandma.data.remote.model.AuthRequest
import com.example.alandma.data.remote.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
  @POST("auth/login")
  suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

  @POST("auth/register")
  suspend fun register(@Body request: AuthRequest): Response<AuthResponse>

  @POST("auth/refresh")
  suspend fun refresh(@Body refreshToken: Map<String, String>): Response<AuthResponse>
}
