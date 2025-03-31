package com.marcelo.dailytrack

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object TODO : BottomNavItem("todo", Icons.Default.Check, "Todo")
    data object PROGRESS : BottomNavItem("in-progress", Icons.Default.Check, "In Progress")
    data object DONE : BottomNavItem("done", Icons.Default.Check, "Done")
}