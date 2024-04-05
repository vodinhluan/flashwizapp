package com.example.flashwiz_fe.presentation.screen
import FlashCardItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.data.model.Flashcard
import com.example.flashwiz_fe.data.network.RetrofitInstance.apiService
import com.example.flashwiz_fe.presentation.components.FolderItem
import com.example.flashwiz_fe.presentation.components.SearchBar
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
