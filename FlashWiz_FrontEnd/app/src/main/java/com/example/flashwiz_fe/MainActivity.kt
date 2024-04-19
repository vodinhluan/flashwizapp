package com.example.flashwiz_fe

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.example.flashwiz_fe.ui.theme.FlashWizTheme
import com.example.flashwiz_fe.ui.theme.gray
import com.example.flashwiz_fe.util.Navigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()

        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            FlashWizTheme(darkTheme = darkTheme) {

                Navigation(darkTheme = darkTheme) {
                    darkTheme = !darkTheme
                }
            }
        }

    }
}