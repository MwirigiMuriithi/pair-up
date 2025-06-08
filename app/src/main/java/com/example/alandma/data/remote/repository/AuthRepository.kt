// app/src/main/java/com/example/alandma/data/remote/repository/AuthRepository.kt
package com.example.alandma.data.remote.repository

import com.example.alandma.data.remote.api.AuthService
import com.example.alandma.data.remote.model.AuthRequest
import com.example.alandma.data.remote.model.AuthResponse
import retrofit2.Response

class AuthRepository(private val service: AuthService) {

    /** 
     * For login we treat the user’s email as the “username” field in AuthRequest,
     * and omit the optional email property. 
     */
    suspend fun login(email: String, password: String): Response<AuthResponse> =
        service.login(
            AuthRequest(
                username = email,
                password = password
            )
        )

    /**
     * For register we pass all three fields explicitly.
     */
    suspend fun register(username: String, email: String, password: String): Response<AuthResponse> =
        service.register(
            AuthRequest(
                username = username,
                email    = email,
                password = password
            )
        )

    suspend fun refreshToken(refreshToken: String): Response<AuthResponse> =
        service.refresh(mapOf("refreshToken" to refreshToken))
}
