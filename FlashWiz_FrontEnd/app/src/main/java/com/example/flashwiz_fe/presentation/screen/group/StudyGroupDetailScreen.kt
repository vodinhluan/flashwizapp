package com.example.flashwiz_fe.presentation.screen.group

import GroupApiService
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.automirrored.filled.PlaylistAddCheck
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.AuthRepositoryImpl
import com.example.flashwiz_fe.domain.model.User

@Composable
fun StudyGroupDetailScreen(
    groupId: Int,
    groupName: String?,
    groupCode: String?,
    navController: NavController,
    groupApiService: GroupApiService,
    context: Context
) {
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.AutoMirrored.Filled.PlaylistAddCheck else Icons.AutoMirrored.Filled.PlaylistAdd
    val userIdsState = mutableStateOf<List<Int>>(emptyList())
    var usersState by remember { mutableStateOf<List<User>>(emptyList()) }
    val userRepository = remember { AuthRepositoryImpl(context) }

    LaunchedEffect(groupId) {
        // Gọi phương thức từ GroupApiService để lấy dữ liệu từ backend
        val groupInfo = groupApiService.getGroup(groupId)
        val userIds = groupInfo["userIds"] as? List<Int> ?: emptyList()
        userIdsState.value=userIds
        Log.d("UserIds","$userIds")
        val users = mutableListOf<User>()
        for (userId in userIds) {
            val user = userRepository.getUserById(userId)
            users.add(user)
        }
        usersState = users
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Tiêu đề nhóm
            Text(
                text = groupName ?: "",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(16.dp)
            )

            // Group Code with Save Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = groupCode ?: "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f),
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Group Code") }
                )
                Icon(
                    imageVector = icon,
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }

            // Danh sách thành viên
            Text(
                text = "Members",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(16.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                usersState.forEach { user ->
                    Surface(
                        modifier = Modifier.padding(4.dp),
                        color = Color.Gray,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = user.name,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // Tiêu đề "Shared Flashcards"
            Text(
                text = "Shared Flashcards",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
