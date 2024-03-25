package com.example.flashwiz_fe.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.Alignment

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