package com.example.flashwiz_fe.presentation.screen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.presentation.state.EnumReviewCard
import com.example.flashwiz_fe.util.ScreenRoutes

@Composable
fun ReviewCardScreen(
    cardViewModel: CardViewModel = hiltViewModel(),
    flashcardId: Int,
    navController: NavController
) {
    val cards by cardViewModel.cardsLiveData.observeAsState()
    val randomCard = cards?.firstOrNull()

    val _cardState = MutableLiveData<EnumReviewCard>(EnumReviewCard.FRONT)
    val cardState: LiveData<EnumReviewCard> = _cardState
    val initialFlashcardId by rememberSaveable { mutableStateOf(flashcardId) }


    LaunchedEffect(Unit) {
        cardViewModel.setInitialFlashcardId(initialFlashcardId)
        cardViewModel.getRandomCardsByFlashcardId(flashcardId) // chạy lần 1
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlippingCard(
                randomCard = randomCard,
                cardViewModel = cardViewModel,
                navController = navController,
                flashcardId = flashcardId
            )
        }
    }

}

@Composable
fun FlippingCard(
    randomCard: CardDetail?,
    cardViewModel: CardViewModel,
    navController: NavController,
    flashcardId: Int
) {
    var rotated by remember { mutableStateOf(false) }
    var showEvaluationBar by remember { mutableStateOf(false) }
    var showBackContent by remember { mutableStateOf(false) }
    val initialFlashcardId by rememberSaveable { mutableStateOf(flashcardId) }


    val rotate by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(600)
    )

    val flashcardId = randomCard?.id ?: 0

    Card(
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffEFEFEF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(320.dp)
            .graphicsLayer {
                rotationY = rotate
                cameraDistance = 10 * density
            }
            .fillMaxWidth()
            .clickable {
                if (!rotated) {
                    rotated = true
                    cardViewModel.onCardFlipped()
                    showEvaluationBar = true // Hiển thị evaluation bar khi lật card
                }
            }
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            val frontText = randomCard?.front ?: "Front text not available"
            val backText = randomCard?.back ?: "Back text not available"
            if (rotate < 90f) {
                Text(
                    text = randomCard?.id?.toString() ?: "",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 16.dp),
                    style = MaterialTheme.typography.h4,
                    color = Color.Blue
                )

                Text(
                    text = randomCard?.rating ?: "",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 76.dp),
                    style = MaterialTheme.typography.h4,
                    color = Color.Blue
                )

                Text(
                    text = frontText,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            } else {
                if (showBackContent && cardViewModel.cardState.value == EnumReviewCard.BACK) {
                    Text(
                        text = backText,
                        fontSize = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.graphicsLayer { rotationY = 180f }
                    )
                }
            }
        }
    }

    if (showEvaluationBar) { // Chỉ hiển thị evaluation bar khi cần thiết
        EvaluationBar(onEvaluationClick = { rating ->
            cardViewModel.setCurrentRating(rating)
            randomCard?.let { card ->
                cardViewModel.removeCurrentCardFromRatingList()
                cardViewModel.updateCardRatingInViewModelScope(card.id, rating)
//                if (cardViewModel.stopRandomCard.value) {
////                    cardViewModel.setStopRandomCard(false)
//                    cardViewModel.setFlashcardId(initialFlashcardId)
//                    navController.navigate("${ScreenRoutes.StatisticScreen.route}/$initialFlashcardId") {
//                    }
//                }
                cardViewModel.getRandomCardsByFlashcardId(card.id) // Random card mới sau khi rating
                rotated = false // Reset trạng thái của card khi random card mới
                showEvaluationBar = false // Ẩn evaluation bar khi random card mới
                showBackContent = false // Ẩn mặt sau của thẻ khi random card mới

            }
        }, cardViewModel = cardViewModel, flashcardId = flashcardId)
    }

    // Sử dụng MutableState để điều khiển việc hiển thị của mặt sau
    LaunchedEffect(cardViewModel.cardState.value) {
        if (cardViewModel.cardState.value == EnumReviewCard.BACK) {
            // Nếu thẻ đang ở mặt sau, đặt biến trạng thái để hiển thị mặt sau là true
            showBackContent = true
        } else {
            // Nếu thẻ đang ở mặt trước, đặt biến trạng thái để hiển thị mặt sau là false
            showBackContent = false
        }
    }
}

@Composable
fun EvaluationBar(
    onEvaluationClick: (String) -> Unit,
    cardViewModel: CardViewModel,
    flashcardId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EvaluationButton(
            text = "Fail",
            color = Color.Red,
            weight = 6f
        ) {
            onEvaluationClick("fail")
        }
        EvaluationButton(
            text = "Hard",
            color = Color.Yellow,
            weight = 6f
        ) {
            onEvaluationClick("hard")
        }
        EvaluationButton(
            text = "Good",
            color = Color.Cyan,
            weight = 6f
        ) {
            onEvaluationClick("good")
        }
        EvaluationButton(
            text = "Easy",
            color = Color.Green,
            weight = 6f
        ) {
            onEvaluationClick("easy")
        }
    }

}

@Composable
fun EvaluationButton(text: String, color: Color, weight: Float, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .padding(horizontal = 2.dp)
            .height(48.dp)
            .background(color, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(horizontal = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black)
    }
}

