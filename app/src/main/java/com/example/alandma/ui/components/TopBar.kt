// AlAndMa/app/src/main/java/com/example/ui/components/CustomTopBar.kt
package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.alandma.R

/**
 * A fully custom top bar that:
 *  • Uses only stable Material-3 theming (no experimental APIs)
 *  • Honors status-bar insets
 *  • Shows a navigation (drawer) icon on the left
 *  • Shows a calendar icon on the right that expands a dropdown of activities
 */
@Composable
fun CustomTopBar(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  height: Dp = 56.dp,
  elevation: Dp = 4.dp,
  contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
  onNavigationClick: (() -> Unit)? = null,
  navigationIcon: @Composable (() -> Unit)? = null,
  calendarActivities: List<String> = emptyList(),
  onCalendarClick: (String) -> Unit = {},
) {
  var calendarMenuOpen by remember { mutableStateOf(false) }

  Surface(
    modifier = modifier
      .fillMaxWidth()
      .zIndex(1f),
    shadowElevation = elevation,
    color = MaterialTheme.colorScheme.primary
  ) {
    Box(
      Modifier
        .windowInsetsPadding(WindowInsets.statusBars)
        .height(height)
    ) {
      Row(
        Modifier
          .fillMaxSize()
          .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Drawer (menu) icon
        if (navigationIcon != null && onNavigationClick != null) {
          IconButton(onClick = onNavigationClick) {
            navigationIcon()
          }
        }

        // Title
        Box(
          Modifier.weight(1f),
          contentAlignment = Alignment.CenterStart
        ) {
          title()
        }

        // Calendar icon + dropdown menu
        if (calendarActivities.isNotEmpty()) {
          IconButton(onClick = { calendarMenuOpen = true }) {
            Icon(
              painter = painterResource(id = R.drawable.ic_calendar),
              contentDescription = "Show activities"
            )
          }
          DropdownMenu(
            expanded = calendarMenuOpen,
            onDismissRequest = { calendarMenuOpen = false }
          ) {
            calendarActivities.forEach { activity ->
              DropdownMenuItem(
                text = { Text(activity) },
                onClick = {
                  calendarMenuOpen = false
                  onCalendarClick(activity)
                }
              )
            }
          }
        }
      }
    }
  }
}
