// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/DreamsScreen/DreamsScreen.kt
package com.example.alandma.ui.screens.DreamsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alandma.data.local.entity.DreamEntity
import com.example.alandma.util.DateUtils
import org.koin.androidx.compose.getViewModel

@Composable
fun DreamsScreen(
    viewModel: DreamsViewModel = getViewModel()
) {
    val dreams by viewModel.dreams.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Dream")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp))
        {
            if (dreams.isEmpty()) {
                Text("No dreams yet.", modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    items(dreams.size) { idx ->
                        val dream = dreams[idx]
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            elevation = 4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = dream.title, style = MaterialTheme.typography.subtitle1)
                                DreamDateRow(dream.targetDateMillis)
                                if (!dream.description.isNullOrBlank()) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = dream.description!!, style = MaterialTheme.typography.body2)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Checkbox(
                                        checked = dream.isAchieved,
                                        onCheckedChange = { isChecked ->
                                            viewModel.toggleAchieved(dream, isChecked)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showAddDialog) {
            AddDreamDialog(
                onDismiss = { showAddDialog = false },
                onSave = { title, desc, targetDateString ->
                    val millis = DateUtils.parseDateToMillis(targetDateString)
                    val newDream = DreamEntity(
                        title = title,
                        description = desc.takeIf { it.isNotBlank() },
                        targetDateMillis = millis
                    )
                    viewModel.addOrUpdateDream(newDream)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
private fun DreamDateRow(millis: Long) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = androidx.compose.material.icons.Icons.Default.CalendarToday,
            contentDescription = null,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(text = DateUtils.formatDate(millis), style = MaterialTheme.typography.body2)
    }
}

@Composable
private fun AddDreamDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, description: String, targetDate: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var targetDate by remember { mutableStateOf("") } // “yyyy-MM-dd”

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Dream / Goal") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = targetDate,
                    onValueChange = { targetDate = it },
                    label = { Text("Target Date (yyyy-MM-dd)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(title, description, targetDate)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
