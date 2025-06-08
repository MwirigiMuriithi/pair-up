// app/src/main/java/com/example/alandma/ui/navigation/AuthNavGraph.kt
package com.example.alandma.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alandma.data.remote.model.AuthResponse
import com.example.alandma.auth.LoginScreen
import com.example.alandma.auth.RegisterScreen

sealed class AuthRoute(val route: String) {
    object Login    : AuthRoute("login")
    object Register : AuthRoute("register")
}

@Composable
fun AuthNavGraph(
    onAuthSuccess: (userId: String, accessToken: String, refreshToken: String) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthRoute.Login.route
    ) {
        composable(AuthRoute.Login.route) {
            LoginScreen(
                onLoginSuccess = { response: AuthResponse ->
                    // forward all three pieces
                    onAuthSuccess(
                        response.userId,
                        response.accessToken,
                        response.refreshToken
                    )
                },
                onNavigateToRegister = {
                    navController.navigate(AuthRoute.Register.route)
                }
            )
        }
        composable(AuthRoute.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { response: AuthResponse ->
                    onAuthSuccess(
                        response.userId,
                        response.accessToken,
                        response.refreshToken
                    )
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}


// // app/src/main/java/com/example/alandma/ui/navigation/AuthNavGraph.kt
// package com.example.alandma.ui.navigation

// import androidx.compose.runtime.Composable
// import androidx.navigation.compose.*
// import com.example.alandma.auth.LoginScreen
// import com.example.alandma.auth.RegisterScreen

// sealed class AuthRoute(val route: String) {
//     object Login    : AuthRoute("login")
//     object Register : AuthRoute("register")
// }

// @Composable
// fun AuthNavGraph(onAuthSuccess: (userId: String) -> Unit) {
//     val nav = rememberNavController()
//     NavHost(navController = nav, startDestination = AuthRoute.Login.route) {
//         composable(AuthRoute.Login.route) {
//             LoginScreen(
//                 onLoginSuccess     = onAuthSuccess,
//                 onNavigateToRegister = { nav.navigate(AuthRoute.Register.route) }
//             )
//         }
//         composable(AuthRoute.Register.route) {
//             RegisterScreen(
//                 onRegisterSuccess = onAuthSuccess,
//                 onNavigateToLogin = { nav.popBackStack() }
//             )
//         }
//     }
// }
