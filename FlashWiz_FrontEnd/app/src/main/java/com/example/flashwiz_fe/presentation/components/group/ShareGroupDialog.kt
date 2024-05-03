package com.example.flashwiz_fe.presentation.components.group

import StudyGroupViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.SecondaryColor

@Composable
fun ShareGroupDialog(
    onDismiss: () -> Unit,
    userId: Int,
    apiService: FolderApiService,
    viewModel: StudyGroupViewModel = StudyGroupViewModel(),
    navController: NavController,
    groupId: Int?
) {
    // Trạng thái để lưu danh sách folder
    var folderList by remember { mutableStateOf<List<FolderDetail>>(emptyList()) }

    // Gọi API để lấy danh sách folder khi dialog được hiển thị
    LaunchedEffect(Unit) {
        folderList = apiService.getFoldersByUserId(userId)
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Share Folder",
                fontFamily = Poppins,
                color = SecondaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        text = {
            LazyColumn {
                items(folderList) { folder ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.selectedFolder?.name == folder.name,
                            onClick = {
                                viewModel.selectedFolder = folder // Lưu folder được chọn vào ViewModel
                            }
                        )
                        Text(
                            text = folder.name ?: "",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // Thực hiện hành động khi nhấn "Cancel", ví dụ: đóng dialog
                        onDismiss()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text("CANCEL")
                }
                Button(
                    onClick = {
                        viewModel.selectedFolder?.let { folder ->
                            // Thực hiện hành động chia sẻ với folder
                            viewModel.selectedFolder = folder
                            viewModel.onSelectedFolderChanged()
                            if (groupId != null) {
                                viewModel.shareFolder(userId, groupId, folder.id) // lỗi
                            }
                            navController.navigateUp()

                        }

                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text("SHARE")
                }
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}
