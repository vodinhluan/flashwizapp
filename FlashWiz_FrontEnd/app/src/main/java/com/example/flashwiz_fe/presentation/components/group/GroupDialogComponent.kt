package com.example.flashwiz_fe.presentation.components.group

import StudyGroupViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.domain.model.GroupDTO
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.SecondaryColor

@Composable
fun GroupDialogComponent(
    userId: Int?,
    onDismiss: () -> Unit,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
    groups: List<GroupDTO>, // Thêm tham số groups
    updateGroups: (List<GroupDTO>) -> Unit // Thêm tham số updateGroups
) {
    var groupCode by remember { mutableStateOf("") } // Biến lưu trữ giá trị của TextField
    val groupViewModel: StudyGroupViewModel = hiltViewModel()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Join Group",
                fontFamily = Poppins,
                color = SecondaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        text ={
            TextField(
                value = groupCode,
                onValueChange = { groupCode = it }, // Cập nhật giá trị của groupCode khi có thay đổi
                enabled = true, // Cho phép nhập dữ liệu
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text("Group Code") }
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = { onDismiss() },
                    modifier = Modifier.padding(8.dp).weight(1f) // Sử dụng weight để cân đối kích thước
                ) {
                    Text(
                        "Cancel",
                        color = SecondaryColor,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                }
                Button(
                    onClick = {
//                        if (userId != null) {
//                            groupViewModel.joinGroupAndUpdateList(
//                                userId = userId,
//                                groupCode = groupCode,
//                                onSuccess = {
//                                    onSuccess()
//                                    onDismiss()
//                                },
//                                onFailure = {
//                                    onFailure()
//                                    onDismiss()
//                                }
//                            ) { updatedGroups ->
//                                updateGroups(updatedGroups)
//                            }
//                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text("Join")
                }
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

