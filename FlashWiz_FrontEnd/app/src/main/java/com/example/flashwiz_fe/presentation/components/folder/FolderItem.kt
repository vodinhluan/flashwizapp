
package com.example.flashwiz_fe.presentation.components

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
import com.example.flashwiz_fe.domain.model.FolderDetail

@OptIn(ExperimentalMaterialApi::class)
// Thay đổi trong FolderItem
@Composable
fun FolderItem(
    folder: FolderDetail, // Thay đổi đối số thành FolderDetail
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

            .clickable(onClick = { onItemClick(folder.id) }), // Truyền folderId khi item được nhấp vào
        border = BorderStroke(1.dp, Color.LightGray),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = folder.name,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = folder.descriptions,
                style = MaterialTheme.typography.body2
            )
        }
    }
}
