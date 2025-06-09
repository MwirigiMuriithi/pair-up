// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/TodayScreen/TodayScreen.kt
package com.example.alandma.ui.screens.TodayScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alandma.ui.components.TaskCard
import com.example.alandma.data.local.entity.TodayTaskEntity
import org.koin.androidx.compose.getViewModel

@Composable
fun TodayScreen(
    viewModel: TodayViewModel = getViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (tasks.isEmpty()) {
                Text(
                    text = "No tasks for today.",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(tasks.size) { idx ->
                        val task = tasks[idx]
                        TaskCard(
                            task = task,
                            onCheckedChange = { isChecked ->
                                viewModel.toggleComplete(task, isChecked)
                            },
                            onClick = {
                                // TODO: open detail/edit
                            }
                        )
                    }
                }
            }
        }

        if (showAddDialog) {
            AddTodayTaskDialog(
                onDismiss = { showAddDialog = false },
                onSave = { title, description, dueMillis ->
                    val newTask = TodayTaskEntity(
                        title = title,
                        description = description,
                        dueDateMillis = dueMillis
                    )
                    viewModel.addOrUpdateTask(newTask)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
private fun AddTodayTaskDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, description: String?, dueDateMillis: Long) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") } // in "yyyy-MM-dd" form

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Task") },
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
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date (yyyy-MM-dd)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val millis = com.example.alandma.util.DateUtils.parseDateToMillis(dueDate)
                onSave(title, description.takeIf { it.isNotBlank() }, millis)
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
