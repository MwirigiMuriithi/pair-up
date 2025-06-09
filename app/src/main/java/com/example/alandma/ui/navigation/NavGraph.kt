// AlAndMa/app/src/main/java/com/example/alandma/ui/navigation/NavGraph.kt
package com.example.alandma.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.alandma.R
import com.example.ui.components.CustomTopBar
import com.example.alandma.ui.screens.BucketScreen.BucketScreen
import com.example.alandma.ui.screens.DreamsScreen.DreamsScreen
import com.example.alandma.ui.screens.HeartsScreen.HeartsScreen
import com.example.alandma.ui.screens.JourneyScreen.JourneyScreen
import com.example.alandma.ui.screens.TodayScreen.TodayScreen
import kotlinx.coroutines.launch

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
        Screen.Today,
        Screen.Journey,
        Screen.Bucket,
        Screen.Hearts,
        Screen.Dreams
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer item 1", Modifier.padding(16.dp))
                Text("Drawer item 2", Modifier.padding(16.dp))
                // more drawer entries here
            }
        }
    ) {
        Scaffold(
            topBar = {
                CustomTopBar(
                    title = {
                        Text(
                            text = "Al&Ma",
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        Icon(Icons.Default.Menu, contentDescription = "Open drawer")
                    },
                    onNavigationClick = {
                        // Open the drawer
                        scope.launch { drawerState.open() }
                    },
                    calendarActivities = listOf(
                        "Morning Prayer",
                        "Team Sync @ 11:00",
                        "Lunch with Alex",
                        "Project Deadline"
                    ),
                    onCalendarClick = { activity ->
                        navController.navigate("activityDetail/${activity}")
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    val backStack by navController.currentBackStackEntryAsState()
                    val currentRoute = backStack?.destination?.route
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
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier
                    .systemBarsPadding()
                    .padding(innerPadding)
            ) {
                composable(Screen.Today.route)   { TodayScreen()   }
                composable(Screen.Journey.route) { JourneyScreen() }
                composable(Screen.Bucket.route)  { BucketScreen()  }
                composable(Screen.Hearts.route)  { HeartsScreen()  }
                composable(Screen.Dreams.route)  { DreamsScreen()  }
                composable("activityDetail/{activity}") { backStackEntry ->
                    val activity = backStackEntry.arguments?.getString("activity")
                    Text(
                      text = "Details for: $activity",
                      modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
