package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.presentation.components.BackIconComponent
import com.example.flashwiz_fe.presentation.components.TextAreaComponent
import com.example.flashwiz_fe.presentation.components.CustomButtonComponent

@Composable
fun CardScreen() {
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
                // Hiển thị AddItemComponent
//                AddItemComponent(expanded = expanded)
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

            TextAreaComponent()

            // BACK CARD
            Text(
                text = "BACK CARD",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )

            TextAreaComponent()

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // SAVE BUTTON
                CustomButtonComponent(
                    text = "Save This Card",
                    onClick = {},
                    modifier = Modifier.wrapContentSize(),
                    backgroundColor = Color.LightGray,
                    contentColor = Color.Blue,
                    borderColor = Color.Black
                )

            }



        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen()
}

