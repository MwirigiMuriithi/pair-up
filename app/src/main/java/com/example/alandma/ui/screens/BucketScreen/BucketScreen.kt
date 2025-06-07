// AlAndMa/app/src/main/java/com/example/alandma/ui/screens/BucketScreen/BucketScreen.kt
package com.example.alandma.ui.screens.BucketScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alandma.ui.components.BucketItemCard
import com.example.alandma.data.local.entity.BucketItemEntity
import org.koin.androidx.compose.getViewModel


@Composable
fun BucketScreen(
    viewModel: BucketViewModel = getViewModel()
) {
    val items by viewModel.items.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Bucket Item")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp))
        {
            if (items.isEmpty()) {
                Text("No bucket items yet.", modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    items(items.size) { idx ->
                        val item = items[idx]
                        BucketItemCard(
                            item = item,
                            onFavoriteToggle = { isFav ->
                                viewModel.toggleFavorite(item, isFav)
                            },
                            onCompleteToggle = { isCompleted ->
                                viewModel.toggleCompleted(item, isCompleted)
                            },
                            onClick = {
                                // TODO: detail or edit
                            }
                        )
                    }
                }
            }
        }

        if (showAddDialog) {
            AddBucketItemDialog(
                onDismiss = { showAddDialog = false },
                onSave = { title, category ->
                    val newItem = BucketItemEntity(
                        title = title,
                        category = category
                    )
                    viewModel.addOrUpdateItem(newItem)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
private fun AddBucketItemDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, category: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Bucket Item") },
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
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category (e.g. Travel)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(title, category)
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
