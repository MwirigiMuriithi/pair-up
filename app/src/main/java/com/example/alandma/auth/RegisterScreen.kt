// app/src/main/java/com/example/alandma/auth/RegisterScreen.kt
package com.example.alandma.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.alandma.data.remote.model.AuthResponse  // ← import added
import org.koin.androidx.compose.getViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: (AuthResponse) -> Unit,        // full response
    onNavigateToLogin: () -> Unit
) {
    val vm: AuthViewModel = getViewModel()
    val uiState by vm.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
    var email    by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { vm.register(username, email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
        Spacer(Modifier.height(8.dp))
        TextButton(
            onClick = onNavigateToLogin,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Already have an account? Log in")
        }
        Spacer(Modifier.height(16.dp))
        when (uiState) {
            AuthUiState.Loading -> CircularProgressIndicator()
            is AuthUiState.Error -> Text(
                text = (uiState as AuthUiState.Error).message,
                color = MaterialTheme.colors.error
            )
            is AuthUiState.Success -> {
                // pull out the full response object
                val response = (uiState as AuthUiState.Success).response
                LaunchedEffect(response) {
                    onRegisterSuccess(response)
                }
            }
            else -> { /* Idle */ }
        }
    }
}

// // app/src/main/java/com/example/alandma/auth/RegisterScreen.kt
// package com.example.alandma.auth

// import androidx.compose.foundation.layout.*
// import androidx.compose.material.*
// import androidx.compose.runtime.*
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.text.input.PasswordVisualTransformation
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.unit.dp
// import org.koin.androidx.compose.getViewModel

// @Composable
// fun RegisterScreen(
//     onRegisterSuccess: (userId: String) -> Unit,
//     onNavigateToLogin: () -> Unit
// ) {
//     val vm: AuthViewModel = getViewModel()
//     val uiState by vm.uiState.collectAsState()

//     var username by remember { mutableStateOf("") }
//     var email by remember { mutableStateOf("") }
//     var password by remember { mutableStateOf("") }

//     Column(
//         Modifier
//             .fillMaxSize()
//             .padding(16.dp),
//         verticalArrangement = Arrangement.Center
//     ) {
//         TextField(
//             value = username,
//             onValueChange = { username = it },
//             label = { Text("Username") },
//             modifier = Modifier.fillMaxWidth()
//         )
//         Spacer(Modifier.height(8.dp))
//         TextField(
//             value = email,
//             onValueChange = { email = it },
//             label = { Text("Email") },
//             modifier = Modifier.fillMaxWidth()
//         )
//         Spacer(Modifier.height(8.dp))
//         TextField(
//             value = password,
//             onValueChange = { password = it },
//             label = { Text("Password") },
//             modifier = Modifier.fillMaxWidth(),
//             visualTransformation = PasswordVisualTransformation()
//         )
//         Spacer(Modifier.height(16.dp))
//         Button(
//             onClick = { vm.register(username, email, password) },
//             modifier = Modifier.fillMaxWidth()
//         ) {
//             Text("Sign Up")
//         }
//         Spacer(Modifier.height(8.dp))
//         TextButton(
//             onClick = onNavigateToLogin,
//             modifier = Modifier.align(Alignment.End)
//         ) {
//             Text("Already have an account? Log in")
//         }
//         Spacer(Modifier.height(16.dp))
//         when (uiState) {
//             AuthUiState.Loading -> CircularProgressIndicator()
//             is AuthUiState.Error -> Text(
//                 (uiState as AuthUiState.Error).message,
//                 color = MaterialTheme.colors.error
//             )
//             is AuthUiState.Success -> {
//                 val userId = (uiState as AuthUiState.Success).userId
//                 LaunchedEffect(userId) { onRegisterSuccess(userId) }
//             }
//             else -> { /* Idle */ }
//         }
//     }
// }
