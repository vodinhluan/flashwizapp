package com.example.flashwiz_fe.presentation.screen.group


import DeleteDialog
import StudyGroupViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.data.remote.GroupApiService
import com.example.flashwiz_fe.domain.model.GroupDTO
import com.example.flashwiz_fe.presentation.components.CustomButtonComponent
import com.example.flashwiz_fe.presentation.components.group.GroupDialogComponent
import com.example.flashwiz_fe.presentation.components.group.GroupItem
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white

@Composable

fun StudyGroupScreen(navController: NavController,
                     groupApiService: GroupApiService,
                     folderApiService: FolderApiService,
                     userId: Int?) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    var originalGroups by remember { mutableStateOf<List<GroupDTO>>(emptyList()) }
    var groups by remember { mutableStateOf<List<GroupDTO>>(emptyList()) }
    var selectedGroup by remember { mutableStateOf<GroupDTO?>(null) }
    var isDataLoaded by remember { mutableStateOf(false) }
    val showHeaderState = remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var isGroupSelected by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var groupIdToDelete by remember { mutableStateOf<Int?>(null) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    val groupViewModel: StudyGroupViewModel = viewModel()


    LaunchedEffect(Unit) {
        originalGroups = groupApiService.getUserGroups(userId)
        groups = originalGroups
        isDataLoaded = true
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showHeaderState.value) {
                if (selectedGroup == null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brightBlue,
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                text = "Study Group",
                                style = MaterialTheme.typography.h4,
                                fontFamily = FontFamily.Cursive,
                                modifier = Modifier.padding(16.dp),
                                color = white,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }else{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brightBlue,
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier
                                    .clickable {
                                        selectedGroup = null
                                    }
                                    .padding(16.dp)
                            )
                            Spacer(modifier = Modifier.width(19.dp))
                            Text(
                                text = "Study Group Detail",
                                style = androidx.compose.material.MaterialTheme.typography.h4,
                                fontFamily = FontFamily.Cursive,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .weight(1f),
                                color = Color.White,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }


            }



            if (isDataLoaded) {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(groups.filter {
                        it.groupName.contains(
                            searchQuery,
                            ignoreCase = true
                        )
                    }) { group ->
                        GroupItem(
                            group = group,
                            onItemClick = { selectedGroupId ->
                                selectedGroupId.let { groupId ->
                                    isGroupSelected = true
                                    selectedGroup = groups.find { it.id == groupId }

                                    Log.d(
                                        "Group ItemClicked",
                                        "Clicked on group with ID: $groupId"
                                    )

                                }
                            },
                            onDeleteClick = { groupId ->
                                groupIdToDelete = groupId
                                showDeleteDialog = true
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                }
                if (!isGroupSelected) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomButtonComponent(
                            text = "Add Group",
                            onClick = {
                                showAddDialog=true
                            },
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        CustomButtonComponent(
                            text = "Join Group",
                            onClick = {
                                showUpdateDialog=true
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            if(showUpdateDialog){
                GroupDialogComponent (
                    userId = userId,
                    onDismiss = {
                        showUpdateDialog = false
                    },
                    onSuccess = {
                        showUpdateDialog = false
                    },
                    onFailure = {
                        showUpdateDialog = false
                    },
                    groups = groups, // Truyền giá trị groups từ StudyGroupScreen
                    updateGroups = { updatedGroups -> // Truyền hàm cập nhật danh sách nhóm
                        groups = updatedGroups // Cập nhật danh sách nhóm mới
                    },
                    title="Join",
                    textfield="Code",
                    isJoinDialog = true
                )
            }

            if(showAddDialog){
                GroupDialogComponent (
                    userId = userId,
                    onDismiss = {
                        showAddDialog = false
                    },
                    onSuccess = {
                        showAddDialog = false
                    },
                    onFailure = {
                        showAddDialog = false
                    },
                    groups = groups, // Truyền giá trị groups từ StudyGroupScreen
                    updateGroups = { updatedGroups -> // Truyền hàm cập nhật danh sách nhóm
                        groups = updatedGroups // Cập nhật danh sách nhóm mới
                    },
                    title="Create",
                    textfield="Name",
                    isJoinDialog = false
                )
            }


            if (showDeleteDialog) {
                groupIdToDelete?.let {
                    DeleteDialog(
                        IdtoDelete = it,
                        onDismiss = { showDeleteDialog = false },
                        itemType = "group",
                        onChangeSuccess = { groupId ->
                            groupViewModel.deleteGroupAndUpdateList(
                                groupId = groupId,
                                viewModel = groupViewModel,
                                apiService = RetrofitInstance.groupApiService,
                                originalGroup = originalGroups
                            ) { updatedGroups ->
                                groups = updatedGroups
                            }
                            showDeleteDialog = false
                        }
                    )
                }
            }

            if (isGroupSelected) {
                selectedGroup?.let { group ->
                    StudyGroupDetailScreen(
                        groupId = group.id,
                        groupName = group.groupName,
                        groupCode = group.groupCode,
                        navController = navController,
                        groupApiService = groupApiService,
                        folderApiService = folderApiService,
                        context = context,
                        userId = userId,
                        onNavigateUp = {
                            selectedGroup = null
                        },

                        )
                }
            }
        }
    }
}