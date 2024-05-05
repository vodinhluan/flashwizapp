package com.example.flashwiz_fe.presentation.components.group

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FolderShared
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.domain.model.GroupDTO

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupItem(
    group: GroupDTO,
    onItemClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(20.dp)
                )

                .shadow(10.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp)), // Áp dụng clip cho nội dung bên trong Card
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onItemClick(group.id) }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Icon(
                        Icons.Outlined.FolderShared,
                        contentDescription = "Group",
                        modifier = Modifier.padding(end = 15.dp)
                    )
                    Text(
                        text = group.groupName,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { onDeleteClick(group.id) }
                    ) {
                        Icon(Icons.Rounded.DeleteOutline, contentDescription = "Delete")
                    }
                }
            }
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