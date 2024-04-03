package com.example.flashwiz_fe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashwiz_fe.util.ScreenRoutes

@Composable
fun AddItemComponent(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded) Icons.Filled.Add else Icons.Outlined.Add

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.clickable { expanded = !expanded }
        )
        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.End)
            ) {
                MenuItem(text = "Add Folder") {
                    navController.navigate(ScreenRoutes.AddFolderScreen.route)
                    expanded = false
                }
            }
        }
    }
}
