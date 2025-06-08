// app/src/main/java/com/example/alandma/data/remote/model/AuthRequest.kt
package com.example.alandma.data.remote.model

data class AuthRequest(
    val username: String,
    val email: String? = null,
    val password: String
)
