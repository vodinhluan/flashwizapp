package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreen(val title: String, val icon: ImageVector) {
    object Statistics : MainScreen("Statistics", Icons.Filled.List)
    object Home : MainScreen("Home", Icons.Filled.Home)
    object Account : MainScreen("Account", Icons.Filled.AccountCircle)

}
