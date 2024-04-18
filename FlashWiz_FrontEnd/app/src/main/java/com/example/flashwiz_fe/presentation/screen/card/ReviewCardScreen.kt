package com.example.flashwiz_fe.presentation.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import kotlinx.coroutines.delay
import androidx.compose.runtime.livedata.observeAsState
import com.example.flashwiz_fe.domain.model.CardDetail


@Composable
fun ReviewCardScreen(cardViewModel: CardViewModel = hiltViewModel(), flashcardId: Int) {
    val cards by cardViewModel.cardsLiveData.observeAsState()
    val randomCard = cards?.firstOrNull()
    LaunchedEffect(Unit) {
        cardViewModel.getRandomCardsByFlashcardId(flashcardId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            FlippingCard(randomCard = randomCard)
        }
    }
}

@Composable
fun FlippingCard(randomCard: CardDetail?, cardViewModel: CardViewModel = hiltViewModel()) {
    var rotated by remember { mutableStateOf(false) }
    var showEvaluationBar by remember { mutableStateOf(false) }
    val rotate by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(600)
    )

    LaunchedEffect(rotated) {
        if (rotated) {
            delay(600)
            showEvaluationBar = true
        } else {
            showEvaluationBar = false
        }
    }

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
            .clickable { rotated = !rotated }
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            val frontText = randomCard?.front ?: "Front text not available"
            val backText = randomCard?.back ?: "Back text not available"
            if (rotate < 90f) {
                Text(
                    text = frontText,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            } else {
                Text(
                    text = backText,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.graphicsLayer { rotationY = 180f }
                )
            }
        }
    }

    // Show evaluation buttons after card is flipped
    AnimatedVisibility(
        visible = showEvaluationBar,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        EvaluationBar { rating ->
            // Gọi đến CardViewModel để cập nhật currentRating ở đây
            cardViewModel.setCurrentRating(rating)
            Log.d("CardViewModel", "Current rating: $rating")
        }    }
}

@Composable
fun EvaluationBar(onEvaluationClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween // Sử dụng SpaceBetween để phân bổ đều
    ) {
        EvaluationButton(text = "Fail", color = Color.Red, weight = 6f) { onEvaluationClick("fail") }
        EvaluationButton(text = "Hard", color = Color.Yellow, weight = 6f) { onEvaluationClick("hard") }
        EvaluationButton(text = "Good", color = Color.Cyan, weight = 6f) { onEvaluationClick("good") }
        EvaluationButton(text = "Easy", color = Color.Green, weight = 6f) { onEvaluationClick("easy") }
    }
}

@Composable
fun EvaluationButton(text: String, color: Color, weight: Float, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .padding(horizontal = 2.dp)
            .height(48.dp) // Cố định chiều cao nút
            .background(color, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(horizontal = 3.dp), // Thêm padding ngang sau khi áp dụng weight
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black)
    }
}