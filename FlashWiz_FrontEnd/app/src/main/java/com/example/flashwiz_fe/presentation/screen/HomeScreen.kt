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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.data.model.Folder
import com.example.flashwiz_fe.data.network.ApiService
import com.example.flashwiz_fe.data.network.RetrofitInstance.apiService
import com.example.flashwiz_fe.presentation.components.AddItemComponent
import com.example.flashwiz_fe.presentation.components.SearchBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
@Composable
fun HomeScreen(apiService: ApiService) {
    var folders by remember { mutableStateOf<List<Folder>>(emptyList()) }

    // Biến trạng thái để xác định xem detail screen có được hiển thị hay không
    var folderDetailShown by remember { mutableStateOf(false) }
    // Dữ liệu của folder được chọn
    var selectedFolderName by remember { mutableStateOf("") }
    var selectedFolderCreateDate by remember { mutableStateOf("") }

    // Biến trạng thái để kiểm soát hiển thị của thanh search và nút điều hướng
    var showSearchBarAndNavButton by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
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
                    // Hiển thị nội dung của màn hình Home
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
                    // Hiển thị AddItemComponent
                    AddItemComponent(expanded = expanded)
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.Cyan),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Nút điều hướng quay về trang Home
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier
                                .clickable {
                                    // Xử lý sự kiện khi nhấp vào nút quay về
                                    folderDetailShown = false // Ẩn detail screen
                                    showSearchBarAndNavButton = true // Hiển thị lại thanh search và nút điều hướng
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
                        AddItemComponent(expanded = expanded)
                    }
                }
            }

            // Hiển thị hoặc ẩn thanh search và nút điều hướng
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
                            // Khi người dùng nhấp vào một mục folder
                            selectedFolderName = folder.name
                            selectedFolderCreateDate = folder.descriptions
                            folderDetailShown = true // Hiển thị detail screen
                            showSearchBarAndNavButton = false // Ẩn thanh search và nút điều hướng
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Khoảng trống giữa các mục
                }
            }

            Spacer(modifier = Modifier.height(100.dp)) // Khoảng trống cho BottomNavigationBar

            // Hiển thị detail screen nếu được chọn
            if (folderDetailShown) {
                FolderDetailScreen(selectedFolderName, selectedFolderCreateDate) {
                    // Khi người dùng nhấn nút điều hướng quay về trang Home
                    showSearchBarAndNavButton = true // Hiển thị lại thanh search và nút điều hướng
                    folderDetailShown = false // Ẩn detail screen
                }
            }
        }
    }
}
