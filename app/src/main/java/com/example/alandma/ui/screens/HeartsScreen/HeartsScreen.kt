// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/HeartsScreen/HeartsScreen.kt
package com.example.alandma.ui.screens.HeartsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alandma.data.local.entity.SpiritualEntryEntity
import org.koin.androidx.compose.getViewModel

@Composable
fun HeartsScreen(
    viewModel: HeartsViewModel = getViewModel()
) {
    val entries by viewModel.entries.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Entry")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp))
        {
            if (entries.isEmpty()) {
                Text("No spiritual entries yet.", modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    items(entries.size) { idx ->
                        val entry = entries[idx]
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            elevation = 4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = entry.title, style = MaterialTheme.typography.subtitle1)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = entry.content, style = MaterialTheme.typography.body2)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = com.example.alandma.util.DateUtils.formatDate(entry.timestampMillis),
                                    style = MaterialTheme.typography.caption
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showAddDialog) {
            AddSpiritualEntryDialog(
                onDismiss = { showAddDialog = false },
                onSave = { title, content ->
                    val newEntry = SpiritualEntryEntity(
                        title = title,
                        content = content,
                        timestampMillis = System.currentTimeMillis()
                    )
                    viewModel.addOrUpdateEntry(newEntry)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
private fun AddSpiritualEntryDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, content: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Spiritual Entry") },
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
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content / Verse / Reflection") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(title, content)
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
