package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.flashwiz_fe.presentation.components.AddItemComponent
import com.example.flashwiz_fe.presentation.components.TextEntryModule
import com.example.flashwiz_fe.ui.theme.orange
import com.example.flashwiz_fe.ui.theme.redOrange

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
                // Hiển thị nội dung của màn hình Home
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
                AddItemComponent(expanded = expanded)
            }

            TextEntryModule(
                description = "Front Card",
                hint = "Type something",
                leadingIcon = Icons.Default.AddCard,
                textValue = "",
                textColor = Color.Black,
                cursorColor = redOrange,
                onValueChanged = {},
                onTrailingIconClick = { /*TODO*/ })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen()
}

