//// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/JourneyScreen/JourneyScreen.kt
//package com.example.alandma.ui.screens.JourneyScreen
package com.example.alandma.ui.screens.JourneyScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alandma.data.local.entity.EventEntity
import com.example.alandma.util.DateUtils
import org.koin.androidx.compose.getViewModel

@Composable
fun JourneyScreen(
    viewModel: JourneyViewModel = getViewModel()
) {
    val events by viewModel.events.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- Screen Header ---
        Text(
            text = "My Journey",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // --- Events List ---
        Text(
            text = "Upcoming / Past Events",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (events.isEmpty()) {
            Text("No events yet.", style = MaterialTheme.typography.body2)
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(events) { event ->
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = 4.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = event.title,
                                style = MaterialTheme.typography.subtitle1
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = DateUtils.formatDate(event.eventDateMillis),
                                style = MaterialTheme.typography.body2
                            )
                            event.description
                                ?.takeIf { it.isNotBlank() }
                                ?.let {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = it, style = MaterialTheme.typography.body2)
                                }
                        }
                    }
                }
            }
        }

        // TODO: Add a FloatingActionButton here to create new events
    }
}

//// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/JourneyScreen/JourneyScreen.kt
//package com.example.alandma.ui.screens.JourneyScreen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
////import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.alandma.data.local.entity.EventEntity
//import com.example.alandma.util.DateUtils
//import com.prolificinteractive.materialcalendarview.CalendarDay
//import com.prolificinteractive.materialcalendarview.MaterialCalendarView
//import androidx.compose.ui.viewinterop.AndroidView
//import org.koin.androidx.compose.getViewModel
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//
//
//@Composable
//fun JourneyScreen(
//    viewModel: JourneyViewModel = getViewModel()
//) {
//    val events by viewModel.events.collectAsState()
//
//    // For simplicity, we’ll use a placeholder: an AndroidView for MaterialCalendarView,
//    // then list below it. In a real app you’d replace this with a Compose-based calendar.
//
//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        // --- Calendar (AndroidView placeholder) ---
//        AndroidView(
//            factory = { context ->
//                MaterialCalendarView(context).apply {
//                    // Configure as needed (e.g., select today)
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Show events in a LazyColumn:
//        Text(text = "Upcoming / Past Events", style = MaterialTheme.typography.h6)
//        Spacer(modifier = Modifier.height(8.dp))
//
//        if (events.isEmpty()) {
//            Text("No events yet.")
//        } else {
//            LazyColumn {
//                items(events) { event ->
//                    Card(
//                        shape = MaterialTheme.shapes.medium,
//                        elevation = 4.dp,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 4.dp)
//                    ) {
//                        Column(modifier = Modifier.padding(12.dp)) {
//                            Text(text = event.title, style = MaterialTheme.typography.subtitle1)
//                            Text(
//                                text = DateUtils.formatDate(event.eventDateMillis),
//                                style = MaterialTheme.typography.body2
//                            )
//                            if (!event.description.isNullOrBlank()) {
//                                Spacer(modifier = Modifier.height(4.dp))
//                                Text(text = event.description!!, style = MaterialTheme.typography.body2)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        // TODO: FloatingActionButton or similar to add a new Event
//    }
//}
