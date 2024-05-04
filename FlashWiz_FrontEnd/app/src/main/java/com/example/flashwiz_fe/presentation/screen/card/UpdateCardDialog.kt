package com.example.flashwiz_fe.presentation.screen.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import com.example.flashwiz_fe.ui.theme.Poppins
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
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        },
        text = {
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                TextField(
                    value = front,
                    onValueChange = { front = it },
                    label = { Text("Front", color = Color.Gray) },
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
                TextField(
                    value = back,
                    onValueChange = { back = it },
                    label = { Text("Back", color = Color.Gray) },
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val updatedCard = card.copy(front = front, back = back)
                    cardViewModel.updateCard(card.id, updatedCard)
                    onChangeSuccess()
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Update this card")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Cancel")
            }
        }
    )

}
//@Preview
//@Composable
//fun UpdateCardDialogPreview() {
//    UpdateCardDialog(
//        card = CardDetail(1, "Front Content", "Back Content","null",),
//        onDismiss = {},
//        onChangeSuccess = {}
//    )
//}
