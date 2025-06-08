// // app/src/main/java/com/example/alandma/auth/CreateGroupScreen.kt
// app/src/main/java/com/example/alandma/auth/CreateGroupScreen.kt
package com.example.alandma.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateGroupScreen(onCreated: (groupId: String) -> Unit) {
    val vm: GroupViewModel = getViewModel()
    val ui by vm.ui.collectAsState()

    var name by remember { mutableStateOf("") }
    var members by remember { mutableStateOf("") } // comma-separated userIds

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = { Text("Group Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = members,
            onValueChange = { newMembers -> members = newMembers },
            label = { Text("User IDs (comma separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val list = members
                    .split(",")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                vm.createGroup(name, list)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Group")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (ui) {
            is GroupUiState.Loading -> {
                CircularProgressIndicator()
            }
            is GroupUiState.Error -> {
                Text(
                    text = (ui as GroupUiState.Error).msg,
                    color = MaterialTheme.colors.error
                )
            }
            is GroupUiState.Success -> {
                val id = (ui as GroupUiState.Success).groupId
                LaunchedEffect(id) {
                    onCreated(id)
                }
            }
            else -> {
                // no-op
            }
        }
    }
}

// package com.example.alandma.auth

// import androidx.compose.foundation.layout.*
// import androidx.compose.material.*
// import androidx.compose.runtime.*
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.dp
// import org.koin.androidx.compose.getViewModel

// @Composable
// fun CreateGroupScreen(onCreated: (groupId: String) -> Unit) {
//     val vm: GroupViewModel = getViewModel()
//     val ui by vm.ui.collectAsState()
//     var name by remember { mutableStateOf("") }
//     var members by remember { mutableStateOf("") } // comma-separated userIds

//     Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
//       TextField(name, { name = it }, label={ Text("Group Name") }, Modifier.fillMaxWidth())
//       Spacer(Modifier.height(8.dp))
//       TextField(members, { members = it },
//         label={ Text("User IDs (comma separated)") }, Modifier.fillMaxWidth()
//       )
//       Spacer(Modifier.height(16.dp))
//       Button(onClick = {
//         val list = members.split(",").map{ it.trim() }.filter{ it.isNotEmpty() }
//         vm.createGroup(name, list)
//       }, Modifier.fillMaxWidth()) {
//         Text("Create Group")
//       }
//       Spacer(Modifier.height(16.dp))
//       when (ui) {
//         is GroupUiState.Loading -> CircularProgressIndicator()
//         is GroupUiState.Error   -> Text((ui as GroupUiState.Error).msg, color=MaterialTheme.colors.error)
//         is GroupUiState.Success -> {
//           val id = (ui as GroupUiState.Success).groupId
//           LaunchedEffect(id) { onCreated(id) }
//         }
//         else -> {}
//       }
//     }
// }
