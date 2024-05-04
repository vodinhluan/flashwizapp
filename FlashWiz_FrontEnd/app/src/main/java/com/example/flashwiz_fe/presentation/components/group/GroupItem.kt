package com.example.flashwiz_fe.presentation.components.group

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.domain.model.Group
import com.example.flashwiz_fe.domain.model.GroupDTO

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupItem(
    group: GroupDTO,
    onItemClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onItemClick(group.id) }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = group.groupName,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { onDeleteClick(group.id) }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            Text(
                text = group.groupCode,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
@Preview
fun PreviewGroupItem() {
    // Tạo một nhóm mẫu
    val group = GroupDTO(id = 1, groupName = "Study Group", groupCode = "SG123")

    GroupItem(
        group = group,
        onItemClick = { println("Clicked on group with ID: $it") },
        onDeleteClick = { println("Clicked delete button on group with ID: $it") }
    )
}
