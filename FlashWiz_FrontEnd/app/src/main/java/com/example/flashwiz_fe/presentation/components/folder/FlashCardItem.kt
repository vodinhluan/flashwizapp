<<<<<<< HEAD
package com.example.flashwiz_fe.presentation.components

=======
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.example.flashwiz_fe.data.model.FolderDetail

@OptIn(ExperimentalMaterialApi::class)
// Thay đổi trong FolderItem
@Composable
fun FolderItem(
    folder: FolderDetail, // Thay đổi đối số thành FolderDetail
    onItemClick: (Int) -> Unit
=======

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlashCardItem(
    flashcardName: String,
    flashcardDescriptions: String,
    onItemClick: () -> Unit // Thêm onItemClick trở lại
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
<<<<<<< HEAD
            .clickable(onClick = { onItemClick(folder.id) }), // Truyền folderId khi item được nhấp vào
        border = BorderStroke(1.dp, Color.LightGray),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
=======
            .clickable(onClick = onItemClick), // Thêm phản hồi khi click
        border = BorderStroke(1.dp, Color.LightGray),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
<<<<<<< HEAD
                text = folder.name,
=======
                text = "Flashcard Name: $flashcardName",
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
<<<<<<< HEAD
                text = folder.descriptions,
=======
                text = "Description: $flashcardDescriptions",
>>>>>>> f51a6747cfc1c55a29c8bbff0539566cfa4f7907
                style = MaterialTheme.typography.body2
            )
        }
    }
}
