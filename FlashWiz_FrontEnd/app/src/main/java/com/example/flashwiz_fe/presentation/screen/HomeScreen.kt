package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.presentation.components.AddItemComponent
import com.example.flashwiz_fe.presentation.components.SearchBar
import com.example.flashwiz_fe.ui.theme.orange

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val expanded = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.Cyan)
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Hiển thị nội dung của màn hình Home
                Text(
                    text = "HOME",
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

            // Search
            SearchBar(
                description = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp,0.dp,10.dp,5.dp),
                hint = "Search",
                textValue = "Search...",
                textColor = Color.Black,
                cursorColor = Color.LightGray,
                onValueChanged = {},
                trailingIcon = Icons.Filled.RemoveRedEye,
                onTrailingIconClick = {}
            )
        }
    }
}
