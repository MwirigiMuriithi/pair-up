// app/src/main/java/com/example/alandma/data/remote/model/AuthResponse.kt
package com.example.alandma.data.remote.model

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
