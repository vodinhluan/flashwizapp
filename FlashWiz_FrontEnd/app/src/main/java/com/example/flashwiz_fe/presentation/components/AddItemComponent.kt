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
    // Trạng thái mở rộng của menu
    var expanded by remember { mutableStateOf(false) }

    // Biểu tượng dấu cộng
    val icon = if (expanded) Icons.Filled.Add else Icons.Outlined.Add

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Hiển thị biểu tượng dấu cộng
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.clickable { expanded = !expanded }
        )
        // Hiển thị DropdownMenu
        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.End)
            ) {
                // Danh sách các mục menu
                MenuItem(text = "Add Folder") {
                    navController.navigate(ScreenRoutes.AddFolderScreen.route)
                    expanded = false // Đóng menu sau khi chuyển đến màn hình thêm thư mục
                }
                // Các mục menu khác có thể được thêm ở đây
            }
        }
    }
}
