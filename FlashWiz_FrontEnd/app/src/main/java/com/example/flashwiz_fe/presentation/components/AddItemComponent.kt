package com.example.flashwiz_fe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.presentation.components.AddFolderDialogComponent
import com.example.flashwiz_fe.presentation.components.MenuItem



@Composable
fun AddItemComponent(expanded: MutableState<Boolean>) {
    // Biểu tượng dấu cộng
    val icon = if (expanded.value) Icons.Filled.Add else Icons.Outlined.Add

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Hiển thị biểu tượng dấu cộng
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.clickable { expanded.value = !expanded.value }
        )
        // Hiển thị DropdownMenu
        if (expanded.value) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.align(Alignment.End)
            ) {
                // Danh sách các mục menu
                MenuItem(text = "Add Folder")
                MenuItem(text = "Add Flashcard")
                MenuItem(text = "Add Card")
            }
        }
    }
}
