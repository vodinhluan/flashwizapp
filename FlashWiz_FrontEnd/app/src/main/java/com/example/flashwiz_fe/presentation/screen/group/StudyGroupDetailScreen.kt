package com.example.flashwiz_fe.presentation.screen.group

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.AuthRepositoryImpl
import com.example.flashwiz_fe.data.FolderRepositoryImpl
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.data.remote.GroupApiService
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.model.User
import com.example.flashwiz_fe.presentation.components.group.AddItemNewGroup
import com.example.flashwiz_fe.presentation.viewmodel.FolderViewModel
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white

@Composable
fun StudyGroupDetailScreen(
    groupId: Int,
    groupName: String?,
    groupCode: String?,
    navController: NavController,
    groupApiService: GroupApiService,
    folderApiService: FolderApiService,
    context: Context,
    userId: Int?
) {
    // Biến mutable state để lưu trữ folder được chọn
    var selectedFolderId by remember { mutableStateOf<Int?>(null) }
    var selectedFlashcardId by remember { mutableStateOf<Int?>(null) }

    val userIdsState = mutableStateOf<List<Int>>(emptyList())
    var usersState by remember { mutableStateOf<List<User>>(emptyList()) }
    val userRepository = remember { AuthRepositoryImpl(context) }

    val folderIdsState = mutableStateOf<List<Int>>(emptyList())
    val folderRepository = remember { FolderRepositoryImpl(folderApiService) }

    // Khai báo viewModel và các mutableState
    val folderViewModel: FolderViewModel = viewModel()
    var folderState by remember { mutableStateOf<List<FolderDetail>>(emptyList()) }
    // Hàm để lấy danh sách flashcard khi nhấp vào folder
    fun getFlashcardsByFolderId(folderId: Int) {
        folderViewModel.getFlashcardsByFolderId(folderId)

    }

    fun getCardsByFlashcardId(flashCardId: Int) {
        folderViewModel.getCardsByFolderId(flashCardId)
    }
    LaunchedEffect(groupId) {
        // Gọi phương thức từ GroupApiService để lấy dữ liệu từ backend
        val groupInfo = groupApiService.getGroup(groupId)
        val userIds = groupInfo["userIds"] as? List<Int> ?: emptyList()
        userIdsState.value = userIds
        Log.d("UserIds", "$userIds")
        val users = mutableListOf<User>()
        for (userId in userIds) {
            val user = userRepository.getUserById(userId)
            users.add(user)
        }
        usersState = users

        val folderIds = groupInfo["folderIds"] as? List<Int> ?: emptyList()
        folderIdsState.value = folderIds
        Log.d("FolderIds", "$folderIds")
        val folders = mutableListOf<FolderDetail>()
        for (folderId in folderIds) {
            val folder = folderRepository.getFolderById(folderId)
            folders.add(folder)
        }
        folderState = folders
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
                    fontWeight = FontWeight.SemiBold
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
                    modifier = Modifier
                        .weight(1f) // Dùng weight để chiếm toàn bộ phần còn lại của Row
                        .padding(end = 16.dp), // Khoảng cách giữa TextField và các phần tử khác
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Group Code") }
                )
            }

            // Danh sách thành viên
            Text(
                text = "Members",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                usersState.forEach { user ->
                    Surface(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        color = Color.Gray,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = user.name,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chia sẻ Folders của bạn",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                // AddItemNewGroup
                AddItemNewGroup(
                    navController = navController,
                    userId = userId,
                    apiService = folderApiService,
                    groupId = groupId
                )
            }

            // Load Danh sách Folder theo user ID ở đây
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                folderState.forEach { folder ->
                    Surface(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                getFlashcardsByFolderId(folder.id)
                                selectedFolderId = folder.id
                                Log.d("FolderId when click", "$selectedFolderId")
                                folderViewModel.cardState.value= emptyList()
                            },
                        color = if (folder.id == selectedFolderId) Color.Green else Color.Blue,
                        shape = MaterialTheme.shapes.extraSmall
                    ) {
                        Text(
                            text = folder.name,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    // Chỉ hiển thị danh sách flashcard của folder được chọn
                    if (folder.id == selectedFolderId) {
                        folderViewModel.flashcardsState.value.forEach { flashcard ->
                            Surface(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable {
                                        getCardsByFlashcardId(flashcard.id)
                                        selectedFlashcardId = flashcard.id
                                        Log.d("FlashcardId when click", "$selectedFlashcardId")
                                    },
                                color = brightBlue,
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(
                                    text = flashcard.name,
                                    style = TextStyle(
                                        color = white,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                            if(flashcard.id == selectedFlashcardId){
                                folderViewModel.cardState.value.forEach  { card ->
                                    Surface(
                                        modifier = Modifier.padding(10.dp),
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            // Hiển thị từ vựng "a:"
                                            Text(
                                                text = card.front,
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                            // Icon hoặc ký tự dấu hai chấm ":"
                                            Text(
                                                text = ":",
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                modifier = Modifier.padding(horizontal = 4.dp)
                                            )
                                            // Hiển thị nghĩa "b"
                                            Text(
                                                text = card.back,
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Normal
                                                )
                                            )
                                        }
                                    }

                                }}
                        }
                    }
                }
            }

        }

    }
}
