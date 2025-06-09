// app/src/main/java/com/example/alandma/auth/LoginScreen.kt
package com.example.alandma.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.alandma.data.remote.model.AuthResponse
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: (AuthResponse) -> Unit, 
    onNavigateToRegister: () -> Unit
) {
    val vm: AuthViewModel = getViewModel()
    val uiState by vm.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { vm.login(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }
        Spacer(Modifier.height(8.dp))
        TextButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Sign Up")
        }
        Spacer(Modifier.height(16.dp))
        when (uiState) {
            is AuthUiState.Loading -> CircularProgressIndicator()
            is AuthUiState.Error   -> Text(
                text = (uiState as AuthUiState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
            is AuthUiState.Success -> {
                // pull out the full response object
                val response = (uiState as AuthUiState.Success).response
                LaunchedEffect(response) {
                    onLoginSuccess(response)
                }
            }
            else -> { /* Idle */ }
        }
    }
}


// app/src/main/java/com/example/alandma/auth/LoginScreen.kt
// package com.example.alandma.auth

// import androidx.compose.foundation.layout.*
// import androidx.compose.material.*
// import androidx.compose.runtime.*
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.text.input.PasswordVisualTransformation
// import androidx.compose.ui.unit.dp
// import org.koin.androidx.compose.getViewModel

// @Composable
// fun LoginScreen(
//     onLoginSuccess: (userId: String) -> Unit,
//     onNavigateToRegister: () -> Unit
// ) {
//     val vm: AuthViewModel = getViewModel()
//     val uiState by vm.uiState.collectAsState()

//     var username by remember { mutableStateOf("") }
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
//             value = password,
//             onValueChange = { password = it },
//             label = { Text("Password") },
//             modifier = Modifier.fillMaxWidth(),
//             visualTransformation = PasswordVisualTransformation()
//         )
//         Spacer(Modifier.height(16.dp))
//         Button(
//             onClick = { vm.login(username, password) },
//             modifier = Modifier.fillMaxWidth()
//         ) {
//             Text("Log In")
//         }
//         Spacer(Modifier.height(8.dp))
//         TextButton(
//             onClick = onNavigateToRegister,
//             modifier = Modifier.align(Alignment.End)
//         ) {
//             Text("Sign Up")
//         }
//         Spacer(Modifier.height(16.dp))
//         when (uiState) {
//             is AuthUiState.Loading -> CircularProgressIndicator()
//             is AuthUiState.Error   -> Text(
//                 (uiState as AuthUiState.Error).message,
//                 color = MaterialTheme.colors.error
//             )
//             is AuthUiState.Success -> {
//                 val userId = (uiState as AuthUiState.Success).userId
//                 LaunchedEffect(userId) { onLoginSuccess(userId) }
//             }
//             else -> { /* Idle */ }
//         }
//     }
// }


// package com.example.alandma.auth

// import androidx.compose.foundation.layout.*
// import androidx.compose.material.*
// import androidx.compose.runtime.*
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.dp
// import org.koin.androidx.compose.getViewModel

// @Composable
// fun LoginScreen(onLoginSuccess: (userId: String) -> Unit) {
//     val vm: AuthViewModel = getViewModel()
//     val uiState by vm.uiState.collectAsState()

//     var username by remember { mutableStateOf("") }
//     var password by remember { mutableStateOf("") }

//     Column(
//         Modifier
//           .fillMaxSize()
//           .padding(16.dp),
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
//             value = password,
//             onValueChange = { password = it },
//             label = { Text("Password") },
//             modifier = Modifier.fillMaxWidth(),
//             visualTransformation = PasswordVisualTransformation()
//         )
//         Spacer(Modifier.height(16.dp))
//         Button(
//             onClick = { vm.login(username, password) },
//             modifier = Modifier.fillMaxWidth()
//         ) {
//             Text("Log In")
//         }
//         Spacer(Modifier.height(8.dp))
//         when (uiState) {
//             is AuthUiState.Loading -> CircularProgressIndicator()
//             is AuthUiState.Error   -> Text((uiState as AuthUiState.Error).message, color = MaterialTheme.colors.error)
//             is AuthUiState.Success -> {
//                 val userId = (uiState as AuthUiState.Success).userId
//                 LaunchedEffect(userId) { onLoginSuccess(userId) }
//             }
//             else -> { /* Idle */ }
//         }
//     }
// }
