package com.example.flashwiz_fe.presentation.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

object DarkModeColors {
    val primaryColor = mutableStateOf(Color.White)
    val secondaryColor = mutableStateOf(Color.Black)
    val backgroundColor = mutableStateOf(Color.Black)
}

@Composable
fun ProvideCustomColors(darkModeEnabled: Boolean, content: @Composable () -> Unit) {
    val primaryColor = if (darkModeEnabled) Color.White else Color.Black
    val secondaryColor = if (darkModeEnabled) Color.Black else Color.White
    val backgroundColor = if (darkModeEnabled) Color.Black else Color.White

    CompositionLocalProvider(
        LocalContentColor provides secondaryColor,
        LocalBackgroundColor provides backgroundColor,
        LocalPrimaryColor provides primaryColor
    ) {
        content()
    }
}

val LocalPrimaryColor = compositionLocalOf<Color> { error("No LocalPrimaryColor provided") }
val LocalContentColor = compositionLocalOf<Color> { error("No LocalContentColor provided") }
val LocalBackgroundColor = compositionLocalOf<Color> { error("No LocalBackgroundColor provided") }
