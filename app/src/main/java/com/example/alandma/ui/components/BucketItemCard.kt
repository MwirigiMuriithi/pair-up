// AlAndMa/app/src/main/java/com/example/alandma/ui/components/BucketItemCard.kt
package com.example.alandma.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alandma.data.local.entity.BucketItemEntity
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder


@Composable
fun BucketItemCard(
    item: BucketItemEntity,
    onFavoriteToggle: (Boolean) -> Unit,
    onCompleteToggle: (Boolean) -> Unit,
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
                Text(text = item.title, style = MaterialTheme.typography.subtitle1)
                Text(text = item.category, style = MaterialTheme.typography.body2)
            }
            Column {
                IconButton(onClick = { onFavoriteToggle(!item.isFavorite) }) {
                    Icon(
                        imageVector = if (item.isFavorite)
                            androidx.compose.material.icons.Icons.Default.Favorite
                        else
                            androidx.compose.material.icons.Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (item.isFavorite) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                    )
                }
                Checkbox(
                    checked = item.isCompleted,
                    onCheckedChange = onCompleteToggle
                )
            }
        }
    }
}
