// AlAndMa/app/src/main/java/com/example/alandma/ui/components/BottomBar.kt
package com.example.alandma.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.alandma.ui.navigation.Screen
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.Today, Screen.Journey, Screen.Bucket, Screen.Hearts, Screen.Dreams
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconRes),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = screen.titleRes)) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}
