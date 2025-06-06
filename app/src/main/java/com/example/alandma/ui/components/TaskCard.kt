// AlAndMa/app/src/main/java/com/example/alandma/ui/components/TaskCard.kt
package com.example.alandma.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alandma.data.local.entity.TodayTaskEntity

@Composable
fun TaskCard(
    task: TodayTaskEntity,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, style = MaterialTheme.typography.subtitle1)
                if (!task.description.isNullOrBlank()) {
                    Text(text = task.description!!, style = MaterialTheme.typography.body2)
                }
            }
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onCheckedChange
            )
        }
    }
}
