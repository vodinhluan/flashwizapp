package com.example.flashwiz_fe.presentation.screen.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun IconButtonWithText(
    text: String,
    isBold: Boolean = false,
    isItalic: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
        )
    }
}
@Preview
@Composable
fun IconButtonWithTextPreview() {
    IconButtonWithText(
        text = "Click me!",
        isBold = true,
        isItalic = false,
        onClick = {}
    )
}
