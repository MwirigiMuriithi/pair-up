// app/src/main/java/com/example/alandma/auth/AuthViewModel.kt
package com.example.alandma.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alandma.data.remote.model.AuthResponse
import com.example.alandma.data.remote.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    // now holds the full AuthResponse
    data class Success(val response: AuthResponse) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val resp = repo.login(email, password)
            if (resp.isSuccessful) {
                resp.body()?.let { auth ->
                    // TODO: you could also persist tokens here if you wanted
                    _uiState.value = AuthUiState.Success(auth)
                } ?: run {
                    _uiState.value = AuthUiState.Error("Empty response")
                }
            } else {
                _uiState.value = AuthUiState.Error("Login failed: ${resp.code()}")
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val resp = repo.register(username, email, password)
            if (resp.isSuccessful) {
                resp.body()?.let { auth ->
                    // TODO: you could also persist tokens here if desired
                    _uiState.value = AuthUiState.Success(auth)
                } ?: run {
                    _uiState.value = AuthUiState.Error("Empty response")
                }
            } else {
                _uiState.value = AuthUiState.Error("Register failed: ${resp.code()}")
            }
        }
    }
}

// // app/src/main/java/com/example/alandma/auth/AuthViewModel.kt
// package com.example.alandma.auth

// import androidx.lifecycle.ViewModel
// import androidx.lifecycle.viewModelScope
// import com.example.alandma.data.remote.repository.AuthRepository
// import kotlinx.coroutines.flow.MutableStateFlow
// import kotlinx.coroutines.flow.StateFlow
// import kotlinx.coroutines.launch

// sealed class AuthUiState {
//     object Idle : AuthUiState()
//     object Loading : AuthUiState()
//     data class Success(val userId: String) : AuthUiState()
//     data class Error(val message: String) : AuthUiState()
// }

// class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

//     private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
//     val uiState: StateFlow<AuthUiState> = _uiState

//     fun login(email: String, password: String) {
//         viewModelScope.launch {
//             _uiState.value = AuthUiState.Loading
//             val resp = repo.login(email, password)
//             if (resp.isSuccessful) {
//                 resp.body()?.let {
//                     // TODO: persist tokens: it.accessToken, it.refreshToken
//                     _uiState.value = AuthUiState.Success(it.userId)
//                 }
//                         ?: run { _uiState.value = AuthUiState.Error("Empty response") }
//             } else {
//                 _uiState.value = AuthUiState.Error("Login failed: ${resp.code()}")
//             }
//         }
//     }

//     fun register(username: String, email: String, password: String) =
//             viewModelScope.launch {
//                 _uiState.value = AuthUiState.Loading
//                 val resp = repo.register(username, email, password)
//                 if (resp.isSuccessful) {
//                     resp.body()?.let {
//                         // TODO: persist tokens
//                         _uiState.value = AuthUiState.Success(it.userId)
//                     }
//                             ?: run { _uiState.value = AuthUiState.Error("Empty response") }
//                 } else {
//                     _uiState.value = AuthUiState.Error("Register failed: ${resp.code()}")
//                 }
//             }
// }
