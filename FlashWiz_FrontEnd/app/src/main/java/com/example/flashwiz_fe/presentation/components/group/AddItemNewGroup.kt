package com.example.flashwiz_fe.presentation.components.group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.presentation.components.MenuItem
//import com.example.flashwiz_fe.presentation.components.ShareGroupDialog

@Composable
fun AddItemNewGroup(
    navController: NavController,
    userId: Int?,
    apiService: FolderApiService,
    groupId: Int?
) {
    var groupName by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.Add else Icons.Outlined.Add
    var showShareGroupDialog by remember { mutableStateOf(false) }

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
//                MenuItem(text = "Create Group") {
//                    navController.navigate(ScreenRoutes.AddStudyGroupScreen.route)
//                }
                MenuItem(text = "Share Folder") {
                    showShareGroupDialog = true
                }
            }
        }
    }

    if (showShareGroupDialog) {
        ShareGroupDialog(
            onDismiss = { showShareGroupDialog = false },
            userId = userId ?: -1,
            apiService = apiService,
            navController = navController,
            groupId = groupId
        )
    }
}
