package com.example.flashwiz_fe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
    private lateinit var PreferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceManager = PreferenceManager(this)
        val initialDarkTheme = PreferenceManager.darkModeEnabled

        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()

        setContent {
            var darkTheme by remember { mutableStateOf(initialDarkTheme) }

            FlashWizTheme(darkTheme = darkTheme) {
                Navigation(darkTheme = darkTheme) {
                    darkTheme = !darkTheme
                    PreferenceManager.darkModeEnabled = darkTheme // Update preference when theme is toggled
                }
            }
        }
    }
}