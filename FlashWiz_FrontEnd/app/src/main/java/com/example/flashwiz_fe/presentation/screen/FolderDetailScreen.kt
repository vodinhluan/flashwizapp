package com.example.flashwiz_fe.presentation.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import FlashCardItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.flashwiz_fe.domain.model.Flashcard
import com.example.flashwiz_fe.data.remote.RetrofitInstance.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun FolderDetailScreen(folderId: Int, folderName: String, description: String, onNavigateUp: () -> Unit) {
    // Tạo một list mutable state để lưu danh sách flashcard
    var flashcards by remember { mutableStateOf<List<Flashcard>>(emptyList()) }

    // Sử dụng LaunchedEffect để gọi API và lấy danh sách flashcard khi FolderDetailScreen được hiển thị
    LaunchedEffect(Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            // Sử dụng folderId nhận được thay vì một giá trị cứng
            flashcards = apiService.getFlashcardsByFolderId(folderId)
        }
    }

    // Hiển thị danh sách flashcard
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Button back và các phần khác giữ nguyên
        }

        // Hiển thị danh sách flashcard
        LazyColumn {
            items(flashcards) { flashcard ->
                FlashCardItem(
                    flashcardName = flashcard.name,
                    flashcardDescriptions = flashcard.descriptions,
                    onItemClick = { /* Xử lý sự kiện khi người dùng nhấp vào mục */ }
                )
            }
        }
    }
}
