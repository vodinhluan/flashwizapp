package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.flashwiz_fe.data.model.Card
import com.example.flashwiz_fe.data.model.FlashcardDetail
import com.example.flashwiz_fe.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.example.flashwiz_fe.presentation.components.folder.CardItemComponent

@Composable
fun FlashcardDetailScreen(
    flashcardId: Int,
    flashcardName: String,
    description: String,
    onNavigateUp: () -> Unit
) {
    // Khai báo mutable state để lưu danh sách card
    var cards by remember { mutableStateOf<List<Card>>(emptyList()) }
    var isDataLoaded by remember { mutableStateOf(false) }

    // Sử dụng LaunchedEffect để gọi API và lấy danh sách card khi màn hình được hiển thị
    LaunchedEffect(Unit) {
        // Gọi API để lấy danh sách card
        cards = RetrofitInstance.apiService.getCardsByFlashcardId(flashcardId)
        isDataLoaded = true // Đánh dấu là dữ liệu đã được tải xuống
    }

    // Hiển thị danh sách card trong một LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(cards) { card ->
            // Hiển thị mỗi card bằng CardItemComponent
            CardItemComponent(
                question = card.back,
                answer = card.front,
                onFlashcardClicked = {
                    // Xử lý sự kiện khi click vào card nếu cần
                }
            )
        }
    }
}
