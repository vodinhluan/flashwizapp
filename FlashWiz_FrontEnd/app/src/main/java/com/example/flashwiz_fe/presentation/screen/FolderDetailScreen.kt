package com.example.flashwiz_fe.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.data.RetrofitInstance.flashcardApiService
import com.example.flashwiz_fe.presentation.components.FlashcardItem
import com.example.flashwiz_fe.presentation.components.home.AddItemComponent
import com.example.flashwiz_fe.presentation.viewmodel.FlashcardViewModel

@Composable
fun FolderDetailScreen(
    folderId: Int,
    folderName: String,
    description: String,
    onNavigateUp: () -> Unit,
    navController: NavController,
    showHeader: MutableState<Boolean>
) {
    val viewModel: FlashcardViewModel = viewModel()
    var originalFlashcard by remember { mutableStateOf<List<FlashcardDetail>>(emptyList()) }
    var flashcards by remember { mutableStateOf<List<FlashcardDetail>>(emptyList()) }
    var selectedFlashcard by remember { mutableStateOf<FlashcardDetail?>(null) }
    LaunchedEffect(Unit) {
        originalFlashcard = flashcardApiService.getFlashcardsByFolderId(folderId)
        flashcards = originalFlashcard
        }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showHeader.value) { // Sử dụng giá trị của showHeader để quyết định việc hiển thị của header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Button back và các phần khác giữ nguyên
            }
        }

        if (selectedFlashcard == null) {
            LazyColumn {
                items(flashcards) { flashcard ->
                    FlashcardItem(
                        flashcard = flashcard,
                        onItemClick = { selectedFlashcardId ->
                            selectedFlashcardId.let { flashcardId ->
                                selectedFlashcard = flashcards.find { it.id == flashcardId }
                                Log.d(
                                    "FlashcardItemClicked",
                                    "Clicked on folder with ID: $flashcardId"
                                )
                            }
                        },
                        onDeleteClick = { flashcardId ->
                            viewModel.deleteFlashcardAndUpdateList(
                                flashcardId = flashcardId,
                                viewModel = viewModel,
                                apiService = flashcardApiService,
                                originalFlashcard = originalFlashcard
                            ) { updatedFlashcards ->
                                flashcards = updatedFlashcards // Cập nhật danh sách thư mục hiển thị
                            }
                        } )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
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
                            selectedFlashcard = null
                            showHeader.value = true // Hiển thị lại header khi quay lại từ màn hình chi tiết flashcard
                        }
                        .padding(16.dp)
                )
                Text(
                    text = "Card",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(16.dp)
                )
                AddItemComponent(navController = navController,"Card",null)
            }
            selectedFlashcard?.let { flashcard ->
                FlashcardDetailScreen(
                    flashcardId = flashcard.id,
                    flashcardName = flashcard.name,
                    description = flashcard.descriptions,
                    onNavigateUp = {
                        selectedFlashcard = null
                        showHeader.value = true // Hiển thị lại header khi quay lại từ màn hình chi tiết flashcard
                    }
                )
            }
        }
    }
}