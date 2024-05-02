package com.example.flashwiz_fe.presentation.screen.group

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.AuthRepositoryImpl
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.data.remote.GroupApiService
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.model.User
import com.example.flashwiz_fe.presentation.components.group.AddItemNewGroup
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import com.example.flashwiz_fe.presentation.viewmodel.FolderViewModel

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
    var expanded by remember { mutableStateOf(false) }
    if (expanded) Icons.AutoMirrored.Filled.PlaylistAddCheck else Icons.AutoMirrored.Filled.PlaylistAdd
//    val userIdsState = mutableStateOf<List<Int>>(emptyList())
//    var usersState by remember { mutableStateOf<List<User>>(emptyList()) }
//    val userRepository = remember { AuthRepositoryImpl(context) }
//
//    val folderIdsState = mutableStateOf<List<Int>>(emptyList())
//    val folderRepository = remember { FolderRepositoryImpl(folderApiService) }
//
//
//    // Khai báo viewModel và các mutableState
//    val folderViewModel: FolderViewModel = viewModel()
//    var folderState by remember { mutableStateOf<List<FolderDetail>>(emptyList()) }
//    var flashcardState by remember { mutableStateOf<List<FlashcardDetail>>(emptyList()) }
//
//    var cardState by remember {
//        mutableStateOf<List<CardDetail>>(emptyList())
//    }
//
//    // Hàm để lấy danh sách flashcard khi nhấp vào folder
//    fun getFlashcardsByFolderId(folderId: Int) {
//        folderViewModel.getFlashcardsByFolderId(folderId)
//        folderViewModel.flashcardsState.value.let {
//            flashcardState = it
//        }
//    }
//
//    fun getCardsByFlashcardId(flashCardId: Int) {
//        folderViewModel.getCardsByFolderId(flashCardId)
//        folderViewModel.cardState.value.let {
//            cardState = it
//        }
//    }
//
//
//    LaunchedEffect(groupId) {
//        // Gọi phương thức từ GroupApiService để lấy dữ liệu từ backend
//        val groupInfo = groupApiService.getGroup(groupId)
//        val userIds = groupInfo["userIds"] as? List<Int> ?: emptyList()
//        userIdsState.value = userIds
//        Log.d("UserIds", "$userIds")
//        val users = mutableListOf<User>()
//        for (userId in userIds) {
//            val user = userRepository.getUserById(userId)
//            users.add(user)
//        }
//        usersState = users
//        // test folder
//        val folderIds = groupInfo["folderIds"] as? List<Int> ?: emptyList()
//        folderIdsState.value = folderIds
//        Log.d("FolderIds", "$folderIds")
//        val folders = mutableListOf<FolderDetail>()
//        for (folderId in folderIds) {
//            val folder = folderRepository.getFolderById(folderId)
//            folders.add(folder)
//        }
//        folderState = folders
//    }

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
//                usersState.forEach { user ->
//                    Surface(
//                        modifier = Modifier.padding(4.dp),
//                        color = Color.Gray,
//                        shape = MaterialTheme.shapes.small
//                    ) {
//                        Text(
//                            text = user.name,
//                            style = TextStyle(
//                                color = Color.White,
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Normal
//                            ),
//                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                        )
//                    }
//                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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
//                AddItemNewGroup(
//                    navController = navController,
//                    userId = userId,
//                    apiService = folderApiService,
//                    groupId = groupId
//                )
            }

            // Load Danh sách Folder theo user ID ở đây
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
//                folderState.forEach { folder ->
//                    Surface(
//                        modifier = Modifier
//                            .padding(4.dp)
//                            .clickable {
//                                getFlashcardsByFolderId(folder.id)
//                            },
//                        color = Color.Blue,
//                        shape = MaterialTheme.shapes.small
//                    ) {
//                        Text(
//                            text = folder.name,
//                            style = TextStyle(
//                                color = Color.White,
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Normal
//                            ),
//                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                        )
//                    }
//                    flashcardState.forEach { flashcard ->
//                        Surface(
//                            modifier = Modifier
//                                .padding(4.dp)
//                                .clickable {
//                                    getCardsByFlashcardId(flashcard.id)
//                                },
//                            color = Color.Cyan,
//                            shape = MaterialTheme.shapes.small
//                        ) {
//                            Text(
//                                text = flashcard.name,
//                                style = TextStyle(
//                                    color = Color.Black,
//                                    fontSize = 12.sp,
//                                    fontWeight = FontWeight.Normal
//                                ),
//                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                            )
//                        }
//
//                        cardState.forEach {
//                                card ->
//                            Surface(
//                                modifier = Modifier
//                                    .padding(4.dp),
//                                color = Color.Yellow,
//                                shape = MaterialTheme.shapes.small
//                            ) {
//                                Text(
//                                    text = card.front +": "+card.back,
//                                    style = TextStyle(
//                                        color = Color.Black,
//                                        fontSize = 12.sp,
//                                        fontWeight = FontWeight.Normal
//                                    ),
//                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                                )
//                            }
//                        }
//                    }
//                }
            }

        }

    }
}

