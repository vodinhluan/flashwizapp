package com.example.flashwiz_fe.presentation.screen.card

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.SecondaryColor
@Composable
fun UpdateCardDialog(
    card: CardDetail,
    onDismiss: () -> Unit,
    onChangeSuccess: () -> Unit
) {
    val cardViewModel: CardViewModel = hiltViewModel()
    var front by remember { mutableStateOf(card.front) }
    var back by remember { mutableStateOf(card.back) }
    val errorMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Update Card",
                fontFamily = Poppins,
                color = SecondaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 30.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        },
        text = {
            Column {
                TextField(
                    value = front,
                    onValueChange = { front = it },
                    label = { Text("Front") },
                    modifier = Modifier.padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { /* Handle action */ }
                    )
                )
                TextField(
                    value = back,
                    onValueChange = { back = it },
                    label = { Text("Back") },
                    modifier = Modifier.padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // Call your update function when the Done button is pressed
                            onDismiss()
                        }
                    )
                )
                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = MaterialTheme.colors.error)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val updatedCard = card.copy(front = front, back = back)

                    Log.d("UpdateCardDialog", "Updated Card: $updatedCard, and Id:${card.id}")
                    cardViewModel.updateCard(card.id, updatedCard)
                    onChangeSuccess()
                }
            ) {
                Text("Update this card")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
