package com.example.flashwiz_fe.presentation.screen.card

import DeleteDialog
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.presentation.components.CustomButtonComponent
import com.example.flashwiz_fe.presentation.components.folder.CardItemComponent
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import com.example.flashwiz_fe.util.ScreenRoutes
import androidx.navigation.NavController
import com.example.flashwiz_fe.ui.theme.SecondaryColor
import androidx.compose.foundation.layout.Column as Column

@Composable
fun FlashcardDetailScreen(
    flashcardId: Int,
    flashcardName: String,
    description: String,
    onNavigateUp: () -> Unit,
    navController: NavController, // điều hướng
    isDarkModeEnabled: Boolean
) {
    val textColor = if (isDarkModeEnabled) Color.White else SecondaryColor
    var originalCard by remember { mutableStateOf<List<CardDetail>>(emptyList()) }
    var cards by remember { mutableStateOf<List<CardDetail>>(emptyList()) }
    var isDataLoaded by remember { mutableStateOf(false) }
    val cardViewModel: CardViewModel = hiltViewModel()
    val updatedCard by cardViewModel.updatedCard.observeAsState()
    var showUpdateCardDialog by remember { mutableStateOf(false) }
    var selectedCard by remember { mutableStateOf<CardDetail?>(null) } // MutableState để lưu trữ thông tin của thẻ được chọn
    var cardIdToDelete by remember { mutableStateOf<Int?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        cardViewModel.getCardsByFlashcardId(flashcardId).let { fetchedCards ->
            originalCard = fetchedCards
            cards = originalCard
            isDataLoaded = true
        }
    }
    if (updatedCard != null) {
        val updatedIndex = cards.indexOfFirst { it.id == updatedCard!!.id }
        if (updatedIndex != -1) {
            val updatedCards = cards.toMutableList()
            updatedCards[updatedIndex] = updatedCard!!
            cards = updatedCards
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = flashcardName, style = MaterialTheme.typography.h4, color = textColor)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, style = MaterialTheme.typography.body1, color = textColor)
        Spacer(modifier = Modifier.height(16.dp))

        if (isDataLoaded) {
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2)
            ) {
                items(cards) { card ->
                    CardItemComponent(
                        card = card,
                        onCardClicked = {
                            cardViewModel.getCardById(card.id)
                            selectedCard = card
                            showUpdateCardDialog = true
                                        },
                        onDeleteClick = { cardId ->
                            cardIdToDelete=cardId
                            showDeleteDialog = true
                        }
                    )
                    if (showDeleteDialog) {
                        cardIdToDelete?.let {
                            DeleteDialog(
                                IdtoDelete = it,
                                onDismiss = { showDeleteDialog = false },
                                itemType="card",
                                onChangeSuccess = { cardId ->
                                    cardViewModel.deleteCardAndUpdateList(
                                        cardId = cardId,
                                        viewModel = cardViewModel,
                                        apiService = RetrofitInstance.cardApiService,
                                        originalCard = originalCard
                                    ) { updatedCards ->
                                        cards = updatedCards
                                    }
                                    showDeleteDialog = false
                                }
                            )
                        }
                    }
                    if (showUpdateCardDialog) {
                        com.example.flashwiz_fe.presentation.screen.card.UpdateCardDialog(
                            card = selectedCard ?: card,
                            onDismiss = { showUpdateCardDialog = false },
                            onChangeSuccess = {
                                showUpdateCardDialog = false
                            }
                        )
                    }
                }
            }
        }
        CustomButtonComponent(
            text = "Review Cards",
            onClick = {
                cardViewModel.setFlashcardId(flashcardId)
                navController.navigate("${ScreenRoutes.ReviewCardScreen.route}/$flashcardId")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            borderColor = Color.Black
        )
    }
}
