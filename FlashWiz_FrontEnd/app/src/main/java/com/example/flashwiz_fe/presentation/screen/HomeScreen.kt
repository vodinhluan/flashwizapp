package com.example.flashwiz_fe.presentation.screen

import FolderItem
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
import com.example.flashwiz_fe.data.model.Folder
import com.example.flashwiz_fe.data.network.ApiService
import com.example.flashwiz_fe.presentation.components.AddItemComponent
import com.example.flashwiz_fe.presentation.components.BackIconComponent
import com.example.flashwiz_fe.presentation.components.SearchBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, apiService: ApiService) {
    var folders by remember { mutableStateOf<List<Folder>>(emptyList()) }

    var folderDetailShown by remember { mutableStateOf(false) }

    var selectedFolderName by remember { mutableStateOf("") }
    var selectedFolderCreateDate by remember { mutableStateOf("") }

    var showSearchBarAndNavButton by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        val expanded = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.Cyan),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (!folderDetailShown) {
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
                    AddItemComponent(navController = navController)
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
                                    folderDetailShown = false
                                    showSearchBarAndNavButton = true
                                }
                                .padding(16.dp)
                        )
                        Text(
                            text = "Folder Detail",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(16.dp)
                        )
                        AddItemComponent(navController = navController)
                    }
                }
            }

            if (showSearchBarAndNavButton) {
                // Search
                SearchBar(
                    description = "Search",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 5.dp),
                    hint = "Search",
                    textValue = "Search...",
                    textColor = Color.Black,
                    cursorColor = Color.LightGray,
                    onValueChanged = {},
                    trailingIcon = Icons.Filled.RemoveRedEye,
                    onTrailingIconClick = {}
                )
            }

            LaunchedEffect(Unit) {
                GlobalScope.launch(Dispatchers.IO) {
                    folders = apiService.getAllFolders()
                }
            }

            // Thanh scrollbar
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(folders) { folder ->
                    FolderItem(
                        folderName = folder.name,
                        createdDate = folder.descriptions,
                        onItemClick = {
                            selectedFolderName = folder.name
                            selectedFolderCreateDate = folder.descriptions
                            folderDetailShown = true
                            showSearchBarAndNavButton = false
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(100.dp))


            if (folderDetailShown) {
                FolderDetailScreen(selectedFolderName, selectedFolderCreateDate) {
                    showSearchBarAndNavButton = true
                    folderDetailShown = false
                }
            }
        }
    }
}




