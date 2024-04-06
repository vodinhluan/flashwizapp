package com.example.flashwiz_fe.presentation.components.folder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun AddFolderDialogComponent() {
    var folderName by remember { mutableStateOf("") }
    var folderDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { /* Xử lý khi Dialog bị hủy bỏ */ },
        title = { Text(text = "Add Folder") },
        buttons = {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* Xử lý khi nhấn nút hủy bỏ */ }) {
                    Text(text = "Cancel")
                }
                TextButton(onClick = { /* Xử lý khi nhấn nút xác nhận */ }) {
                    Text(text = "Add")
                }
            }
        },
        text = {
            Column {
                TextField(
                    value = folderName,
                    onValueChange = { folderName = it },
                    label = { Text(text = "Folder Name") }
                )
                TextField(
                    value = folderDescription,
                    onValueChange = { folderDescription = it },
                    label = { Text(text = "Folder Description") }
                )
            }
        }
    )
}