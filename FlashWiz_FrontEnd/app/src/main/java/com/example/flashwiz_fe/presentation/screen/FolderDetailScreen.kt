package com.example.flashwiz_fe.presentation.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FolderDetailScreen(folderName: String, createdDate: String, onNavigateUp: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị thông tin về thư mục
        Text(text = "Folder Name: $folderName")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Created Date: $createdDate")
        // Thêm thông tin khác về thư mục ở đây
    }}
