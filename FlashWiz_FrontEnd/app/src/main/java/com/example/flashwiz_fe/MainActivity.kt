package com.example.flashwiz_fe



import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.flashwiz_fe.domain.model.PreferenceManager
import com.example.flashwiz_fe.ui.theme.FlashWizTheme
import com.example.flashwiz_fe.ui.theme.gray
import com.example.flashwiz_fe.util.Navigation
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        requestNotificationPermission()
        super.onCreate(savedInstanceState)
        preferenceManager = PreferenceManager(this)
        val initialDarkTheme = preferenceManager.darkModeEnabled

        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()

        setContent {
            FlashWizApp(initialDarkTheme, preferenceManager)
        }
    }
    private fun requestNotificationPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if(!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
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





