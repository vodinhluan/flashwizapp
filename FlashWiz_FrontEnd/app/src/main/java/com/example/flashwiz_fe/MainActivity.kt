package com.example.flashwiz_fe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
           FlashWizTheme {
                Navigation()
            }
        }

    }
}
