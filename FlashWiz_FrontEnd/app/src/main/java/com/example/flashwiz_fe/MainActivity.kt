package com.example.flashwiz_fe

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import com.example.flashwiz_fe.domain.model.PreferenceManager
import com.example.flashwiz_fe.ui.theme.FlashWizTheme
import com.example.flashwiz_fe.ui.theme.gray
import com.example.flashwiz_fe.util.Navigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager = PreferenceManager(this)
        val initialDarkTheme = preferenceManager.darkModeEnabled

        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()

        setContent {
            FlashWizApp(initialDarkTheme, preferenceManager)
        }
    }
}

@Composable
fun FlashWizApp(initialDarkTheme: Boolean, preferenceManager: PreferenceManager) {
    var darkTheme by remember { mutableStateOf(initialDarkTheme) }

    FlashWizTheme(darkTheme = darkTheme) {
        Navigation(darkTheme = darkTheme) {
            darkTheme = !darkTheme
            preferenceManager.darkModeEnabled = darkTheme // Update preference when theme is toggled
        }
    }
}