package com.example.flashwiz_fe.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MenuItem(text: String) {
    Text(
        text = text,
        modifier = Modifier.run {
            padding(vertical = 8.dp, horizontal = 16.dp)
                .clickable(){}
        }
    )
}
