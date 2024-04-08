package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.data.RetrofitInstance
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.presentation.components.folder.CardItemComponent

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@Composable
fun FlashcardDetailScreen(
    flashcardId: Int,
    flashcardName: String,
    description: String,
    onNavigateUp: () -> Unit
) {
    var cards by remember { mutableStateOf<List<Card>>(emptyList()) }
    var isDataLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        cards = RetrofitInstance.cardApiService.getCardsByFlashcardId(flashcardId)
        isDataLoaded = true
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Tiêu đề và mô tả
        Text(text = flashcardName, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách các card hiển thị theo grid
        if (isDataLoaded) {
            LazyVerticalGrid(
                modifier = Modifier.weight(1f), // Chiếm toàn bộ không gian còn lại
                columns = GridCells.Fixed(2)
            ) {
                items(cards) { card ->
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
    }
}

