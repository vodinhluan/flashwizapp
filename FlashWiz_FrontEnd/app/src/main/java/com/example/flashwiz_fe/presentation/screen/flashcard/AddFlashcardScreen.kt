@file:Suppress("NAME_SHADOWING")
package com.example.flashwiz_fe.presentation.screen.flashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.presentation.viewmodel.FlashcardViewModel

@Composable
fun AddFlashcardScreen(
    onNavigateBack: (FlashcardDetail?) -> Unit,
    initialFolderId: Int?,
    navController: NavHostController,
) {
    val selectedFlashcard by remember { mutableStateOf<FlashcardDetail?>(null) }
    val viewModel: FlashcardViewModel = viewModel()
    var flashcardName by remember { mutableStateOf("") }
    var flashcardDescription by remember { mutableStateOf("") }


    var isFromSelectedFolder by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add Flashcard",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = flashcardName,
            onValueChange = { flashcardName = it },
            label = { Text("Flashcard Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = flashcardDescription,
            onValueChange = { flashcardDescription = it },
            label = { Text("Flashcard Description") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        onNavigateBack(selectedFlashcard)
                    }
                    .padding(16.dp)
            )

            Button(
                onClick = {
                    if (initialFolderId != null) {
                        viewModel.addFlashcard(
                            flashcardName,
                            flashcardDescription,
                            initialFolderId
                        ) {
                            onNavigateBack(selectedFlashcard)
                        }
                    }
                }
            ) {
                Text("Add Flashcard")
            }
        }
    }
}






