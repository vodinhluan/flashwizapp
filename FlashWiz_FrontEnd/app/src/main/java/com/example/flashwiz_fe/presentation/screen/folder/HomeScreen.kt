package com.example.flashwiz_fe.presentation.screen.folder

import DeleteDialog
import FolderItem
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.presentation.components.home.AddItemComponent
import com.example.flashwiz_fe.presentation.components.home.SearchBar
import com.example.flashwiz_fe.presentation.screen.flashcard.FolderDetailScreen
import com.example.flashwiz_fe.presentation.viewmodel.FolderViewModel
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white

@Composable
fun HomeScreen(navController: NavController, apiService: FolderApiService,userId: Int?, isDarkMode: Boolean) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val viewModel: FolderViewModel = viewModel()
    var originalFolders by remember { mutableStateOf<List<FolderDetail>>(emptyList()) }
    var folders by remember { mutableStateOf<List<FolderDetail>>(emptyList()) }
    var selectedFolder by remember { mutableStateOf<FolderDetail?>(null) }
    var isDataLoaded by remember { mutableStateOf(false) }
    val showHeaderState = remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var isFolderSelected by remember { mutableStateOf(false) }

//    val isDarkMode by viewModel.darkThemeEnabled.observeAsState(false)



    var showDeleteDialog by remember { mutableStateOf(false) }
    var folderIdToDelete by remember { mutableStateOf<Int?>(null) }
    var headerText by remember { mutableStateOf("Your Folders") }


    val userNameState = remember { mutableStateOf("") }

    LaunchedEffect(userNameState.value) {
        val userName = userPreferences.getUserName()
        userNameState.value = (userName ?: "").toString()
    }
    val userName by remember { derivedStateOf { userNameState.value } }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (isDarkMode) Color.Black else Color.White
    ) {
        val expanded = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showHeaderState.value) {
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
                        .padding(0.dp, 0.dp, 0.dp, 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selectedFolder == null) {
                            val welcomeText = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                    append("Welcome, ")
                                }
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                                    append(userName)
                                }
                            }
                            Text(
                                text = welcomeText,
                                style = MaterialTheme.typography.h4,
                                fontFamily = FontFamily.Cursive,
                                modifier = Modifier.padding(16.dp),
                                color = white,
                                fontWeight = FontWeight.SemiBold
                            )

                            AddItemComponent(
                                navController = navController,
                                "Folder",
                                null,
                                null,
                                userId
                            )
                        } else {
                            isFolderSelected = true
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .clickable {
                                            selectedFolder = null
                                        }
                                        .padding(16.dp)
                                )
                                Text(
                                    text = "Flashcard",
                                    style = MaterialTheme.typography.h4,
                                    fontFamily = FontFamily.Cursive,
                                    color = white,
                                    modifier = Modifier.padding(16.dp),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold
                                )
                                selectedFolder?.let { folder ->
                                    AddItemComponent(
                                        navController = navController,
                                        "Flashcard",
                                        folderId = folder.id,
                                        null, null
                                    )
                                }
                            }
                        }
                    }
                    SearchBar(
                        description = "Tìm kiếm",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp, 10.dp, 0.dp),
                        hint = "Tìm kiếm",
                        textValue = searchQuery,
                        textColor = Color.Black,
                        cursorColor = Color.Black,
                        onValueChanged = { newValue ->
                            searchQuery = newValue
                        },
                        trailingIcon = Icons.Default.Search,
                        onTrailingIconClick = {}
                    )
                }
            }
                LaunchedEffect(Unit) {
                    originalFolders = apiService.getFoldersByUserId(userId)
                    folders = originalFolders
                    isDataLoaded = true
                }
                if (isDataLoaded) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(folders.filter {
                            it.name.contains(
                                searchQuery,
                                ignoreCase = true
                            ) || it.descriptions.contains(searchQuery, ignoreCase = true)
                        }) { folder ->
                            FolderItem(
                                folder = folder,
                                onItemClick = { selectedFolderId ->
                                    selectedFolderId.let { folderId ->
                                        isFolderSelected = true
                                        selectedFolder = folders.find { it.id == folderId }
                                        Log.d(
                                            "FolderItemClicked",
                                            "Clicked on folder with ID: $folderId"
                                        )
                                    }
                                },
                                onDeleteClick = { folderId ->
                                    folderIdToDelete = folderId
                                    showDeleteDialog = true
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                if (showDeleteDialog) {
                    folderIdToDelete?.let {
                        DeleteDialog(
                            IdtoDelete = it,
                            onDismiss = { showDeleteDialog = false },
                            itemType = "folder",
                            onChangeSuccess = { folderId ->
                                viewModel.deleteFolderAndUpdateList(
                                    folderId = folderId,
                                    viewModel = viewModel,
                                    apiService = RetrofitInstance.folderApiService,
                                    originalFolders = originalFolders
                                ) { updatedFolders ->
                                    folders = updatedFolders
                                }
                                showDeleteDialog = false
                            }

                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                selectedFolder?.let { folder ->
                    FolderDetailScreen(
                        folderId = folder.id,
                        folderName = folder.name,
                        description = folder.descriptions,
                        onNavigateUp = {
                            selectedFolder = null
                        },
                        isFolderSelected = true,
                        navController = navController,
                        showHeader = showHeaderState,
                        isDarkModeEnabled = isDarkMode
                    )
                }
        }
    }
}


