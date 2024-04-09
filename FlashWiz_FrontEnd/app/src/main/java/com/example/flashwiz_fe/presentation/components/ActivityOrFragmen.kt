package com.example.flashwiz_fe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DarkModeSample(darkModeEnabled: Boolean) {
    ProvideCustomColors(darkModeEnabled) {
        // Sử dụng các giá trị màu sắc từ CompositionLocal ở đây
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(LocalBackgroundColor.current)
        )
        // Giao diện người dùng của bạn ở đây
    }
}
