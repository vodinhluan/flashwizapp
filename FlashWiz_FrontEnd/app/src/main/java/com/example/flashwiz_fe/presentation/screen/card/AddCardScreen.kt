package com.example.flashwiz_fe.presentation.screen.card

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.presentation.components.CustomButtonComponent
import com.example.flashwiz_fe.presentation.state.CardState
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun AddCardScreen(cardViewModel: CardViewModel,  navController: NavHostController) {
    val cardState = remember { mutableStateOf(CardState()) }
    val saveSuccess by cardViewModel.saveSuccess.collectAsState()
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val expanded = remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "CARD INFO",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // FRONT CARD
            Text(
                text = "FRONT CARD",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
            )

            TextField(
                value = cardState.value.frontText,
                onValueChange = { cardState.value = cardState.value.copy(frontText = it) },
                label = { Text("") },
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // BACK CARD
            Text(
                text = "BACK CARD",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(5.dp)
            )

            TextField(
                value = cardState.value.backText,
                onValueChange = { cardState.value = cardState.value.copy(backText = it) },
                label = { Text("") },
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // SAVE BUTTON
                CustomButtonComponent(
                    text = "Save This Card",
                    onClick = {
                        val card = Card(
                            front = cardState.value.frontText.trim(),
                            back = cardState.value.backText.trim()
                        )
                        cardViewModel.saveCard(card)
                    },
                    modifier = Modifier.wrapContentSize(),
                    backgroundColor = Color.LightGray,
                    contentColor = Color.Blue,
                    borderColor = Color.Black
                )

            }
            LaunchedEffect(saveSuccess) {
                if (saveSuccess) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}










