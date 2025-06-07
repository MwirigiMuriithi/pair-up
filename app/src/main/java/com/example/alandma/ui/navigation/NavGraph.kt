// AlAndMa/app/src/main/java/com/example/alandma/ui/navigation/NavGraph.kt
package com.example.alandma.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.material.Scaffold

import com.example.alandma.R
import com.example.alandma.ui.screens.BucketScreen.BucketScreen
import com.example.alandma.ui.screens.DreamsScreen.DreamsScreen
import com.example.alandma.ui.screens.HeartsScreen.HeartsScreen
import com.example.alandma.ui.screens.JourneyScreen.JourneyScreen
import com.example.alandma.ui.screens.TodayScreen.TodayScreen

sealed class Screen(val route: String, val iconRes: Int, val titleRes: Int) {
    object Today   : Screen("today",   R.drawable.ic_today,   R.string.tab_today)
    object Journey : Screen("journey", R.drawable.ic_journey, R.string.tab_journey)
    object Bucket  : Screen("bucket",  R.drawable.ic_bucket,  R.string.tab_bucket)
    object Hearts  : Screen("hearts",  R.drawable.ic_hearts,  R.string.tab_hearts)
    object Dreams  : Screen("dreams",  R.drawable.ic_dreams,  R.string.tab_dreams)
}

@Composable
fun NavGraph(startDestination: String = Screen.Today.route) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Today, Screen.Journey, Screen.Bucket, Screen.Hearts, Screen.Dreams
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val currentRoute = navController
                    .currentBackStackEntryAsState().value?.destination?.route
                items.forEach { screen ->
                    BottomNavigationItem(
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
//            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(Screen.Today.route)   { TodayScreen()   }
            composable(Screen.Journey.route) { JourneyScreen() }
            composable(Screen.Bucket.route)  { BucketScreen()  }
            composable(Screen.Hearts.route)  { HeartsScreen()  }
            composable(Screen.Dreams.route)  { DreamsScreen()  }
        }
    }
}
