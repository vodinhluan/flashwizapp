package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun AccountScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Hiển thị nội dung của màn hình Settings
        Text(text = "Đây là trang tài khoản", modifier = Modifier.fillMaxSize())
    }}