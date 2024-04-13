package com.example.flashwiz_fe.presentation.screen

import android.util.Log
import com.example.flashwiz_fe.presentation.components.FolderItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.flashwiz_fe.presentation.components.home.AddItemComponent
import com.example.flashwiz_fe.presentation.components.home.SearchBar
import com.example.flashwiz_fe.domain.model.FolderDetail





import com.example.flashwiz_fe.data.remote.FolderApiService


@Composable
fun HomeScreen(navController: NavController, apiService: FolderApiService) {
    var folders by remember { mutableStateOf<List<FolderDetail>>(emptyList()) }
    var selectedFolder by remember { mutableStateOf<FolderDetail?>(null) }
    var isDataLoaded by remember { mutableStateOf(false) }
    val showHeaderState = remember { mutableStateOf(true) } // Thêm biến showHeaderState

    var searchQuery by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showHeaderState.value) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.Cyan),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (selectedFolder == null) {
                        Text(
                            text = "HOME",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(16.dp)
                        )
                        AddItemComponent(navController = navController, "Folder")
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.Cyan),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black,
                                modifier = Modifier
                                    .clickable {
                                        selectedFolder = null
                                    }
                                    .padding(16.dp)
                            )
                            Text(
                                text = "Flashcard",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(16.dp)
                            )
                            AddItemComponent(navController = navController, "Flashcard")
                        }
                    }
                }
                SearchBar(
                    description = "Search",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 5.dp),
                    hint = "Search",
                    textValue = searchQuery,
                    textColor = Color.Black,
                    cursorColor = Color.LightGray,
                    onValueChanged = { newValue ->
                        searchQuery = newValue
                    }, trailingIcon = Icons.Filled.RemoveRedEye,
                    onTrailingIconClick = {}
                )
            }



            LaunchedEffect(Unit) {
                folders = apiService.getAllFolders()
                isDataLoaded = true // Đặt biến trạng thái là true khi dữ liệu đã được tải xuống
            }

            // Thanh scrollbar
            if (isDataLoaded) { // Chỉ hiển thị khi dữ liệu đã được tải xuống
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
                            folder = folder
                        ) { selectedFolderId ->
                            selectedFolderId.let { folderId ->
                                selectedFolder = folders.find { it.id == folderId }
                                Log.d(
                                    "FolderItemClicked",
                                    "Clicked on folder with ID: $folderId"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                    }
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
                    navController = navController,
                    showHeader = showHeaderState
                )
            }
        }
    }
}
