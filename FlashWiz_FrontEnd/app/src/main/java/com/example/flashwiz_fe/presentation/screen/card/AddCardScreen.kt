package com.example.flashwiz_fe.presentation.screen.card

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
fun AddCardScreen(cardViewModel: CardViewModel,  navController: NavHostController,initialFlashcardId: Int?) {
    val cardState = remember { mutableStateOf(CardState()) }
    val saveSuccess by cardViewModel.saveSuccess.collectAsState()
    val context = LocalContext.current
    var isBoldFront by remember { mutableStateOf(false) }
    var isItalicFront by remember { mutableStateOf(false) }
    var isBoldBack by remember { mutableStateOf(false) }
    var isItalicBack by remember { mutableStateOf(false) }
    var textAlignFront by remember { mutableStateOf(TextAlign.Start) }
    var textAlignBack by remember { mutableStateOf(TextAlign.Start) }


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
                    text = "CARD INFO $initialFlashcardId",
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

// Add B and I Buttons for Front End
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonWithText(
                    text = "B",
                    isBold = isBoldFront,
                    onClick = { isBoldFront = !isBoldFront }
                )
                IconButtonWithText(
                    text = "I",
                    isItalic = isItalicFront,
                    onClick = { isItalicFront = !isItalicFront }
                )
                IconButtonWithText(
                    text = "L",
                    onClick = { textAlignFront = TextAlign.Start }
                )
                IconButtonWithText(
                    text = "C",
                    onClick = { textAlignFront = TextAlign.Center }
                )
                IconButtonWithText(
                    text = "R",
                    onClick = { textAlignFront = TextAlign.End }
                )
            }

            TextField(
                value = cardState.value.frontText,
                onValueChange = { cardState.value = cardState.value.copy(frontText = it) },
                label = { Text("") },
                textStyle = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    fontWeight = if (isBoldFront) FontWeight.Bold else FontWeight.Normal,
                    fontStyle = if (isItalicFront) FontStyle.Italic else FontStyle.Normal,
                    textAlign = textAlignFront
                ),
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(175.dp)
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
                modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
            )

// Add B and I Buttons for Back End
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonWithText(
                    text = "B",
                    isBold = isBoldBack,
                    onClick = { isBoldBack = !isBoldBack }
                )
                IconButtonWithText(
                    text = "I",
                    isItalic = isItalicBack,
                    onClick = { isItalicBack = !isItalicBack }
                )
                IconButtonWithText(
                    text = "L",
                    onClick = { textAlignBack = TextAlign.Start }
                )
                IconButtonWithText(
                    text = "C",
                    onClick = { textAlignBack = TextAlign.Center }
                )
                IconButtonWithText(
                    text = "R",
                    onClick = { textAlignBack = TextAlign.End }
                )
            }

            TextField(
                value = cardState.value.backText,
                onValueChange = { cardState.value = cardState.value.copy(backText = it) },
                label = { Text("") },
                textStyle = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    fontWeight = if (isBoldBack) FontWeight.Bold else FontWeight.Normal,
                    fontStyle = if (isItalicBack) FontStyle.Italic else FontStyle.Normal,
                    textAlign = textAlignBack
                ),
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(175.dp)
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
//                        cardViewModel.saveCard(card) Phu Le da Comment


                        Log.d("AddCardScreen", "Flashcard ID: $initialFlashcardId")
                        if (initialFlashcardId != null) {
                            cardViewModel.saveCard(
                                Card(
                                    front = cardState.value.frontText.trim(),
                                    back = cardState.value.backText.trim()
                                ),
                                initialFlashcardId
                            )
                        }
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

