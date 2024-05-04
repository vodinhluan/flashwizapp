package com.example.flashwiz_fe.presentation.screen.flashcard

import DeleteDialog
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.RetrofitInstance.flashcardApiService
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.presentation.components.FlashcardItem
import com.example.flashwiz_fe.presentation.components.home.AddItemComponent
import com.example.flashwiz_fe.presentation.screen.card.FlashcardDetailScreen
import com.example.flashwiz_fe.presentation.viewmodel.FlashcardViewModel
import com.example.flashwiz_fe.ui.theme.SecondaryColor
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white

@Composable
fun FolderDetailScreen(
    folderId: Int,
    folderName: String,
    description: String,
    onNavigateUp: () -> Unit,
    navController: NavController,
    showHeader: MutableState<Boolean>,
    isFolderSelected:Boolean,
    isDarkModeEnabled: Boolean
) {
    val textColor = if (isDarkModeEnabled) Color.White else SecondaryColor
    val viewModel: FlashcardViewModel = viewModel()
    var originalFlashcard by remember { mutableStateOf<List<FlashcardDetail>>(emptyList()) }
    var flashcards by remember { mutableStateOf<List<FlashcardDetail>>(emptyList()) }
    var selectedFlashcard by remember { mutableStateOf<FlashcardDetail?>(null) }
    var flashcardIdToDelete by remember { mutableStateOf<Int?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        originalFlashcard = flashcardApiService.getFlashcardsByFolderId(folderId)
        flashcards = originalFlashcard
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showHeader.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

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
                                showHeader.value =
                                    false
                            }
                        },
                        onDeleteClick = { flashcardId ->
                            flashcardIdToDelete = flashcardId
                            showDeleteDialog = true
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (showDeleteDialog) {
                flashcardIdToDelete?.let {
                    DeleteDialog(
                        IdtoDelete = it,
                        onDismiss = { showDeleteDialog = false },
                        itemType = "flashcard",
                        onChangeSuccess = { flashcardId ->
                            viewModel.deleteFlashcardAndUpdateList(
                                flashcardId = flashcardId,
                                viewModel = viewModel,
                                apiService = RetrofitInstance.flashcardApiService,
                                originalFlashcard = originalFlashcard
                            ) { updatedFlashcards ->
                                flashcards = updatedFlashcards
                            }
                            showDeleteDialog = false
                        }
                    )
                }
            }
        } else {
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

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            selectedFlashcard = null
                            showHeader.value =
                                true
                        }
                        .padding(16.dp)
                )
                Text(
                    text = "Card",
                    style = MaterialTheme.typography.h4,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    color = white,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.SemiBold
                )
                selectedFlashcard?.let { flashcard ->
                    AddItemComponent(
                        navController = navController,
                        "Card",
                        null,
                        flashcardId = flashcard.id, null
                     )

                    }
                }
            }
            selectedFlashcard?.let { flashcard ->
                FlashcardDetailScreen(
                    flashcardId = flashcard.id,
                    flashcardName = flashcard.name,
                    description = flashcard.descriptions,
                    onNavigateUp = {
                        selectedFlashcard = null
                        showHeader.value = true
                    },
                    navController,
                    isDarkModeEnabled
                )
            }
        }
    }
}