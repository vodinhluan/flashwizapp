package com.example.flashwiz_fe.presentation.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashwiz_fe.presentation.viewmodel.ThemeViewModel
import com.example.flashwiz_fe.ui.theme.DarkColors
import com.example.flashwiz_fe.ui.theme.LightColors
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.Shapes



import com.example.flashwiz_fe.presentation.components.setting.DarkModeSwitch
import com.example.flashwiz_fe.presentation.components.setting.GeneralOptionsUI
import com.example.flashwiz_fe.presentation.components.setting.LogoutUI
import com.example.flashwiz_fe.presentation.components.setting.ProfileCardUI


@Composable
fun AccountScreen(themeViewModel: ThemeViewModel = viewModel()) {
    val isDarkModeEnabled by themeViewModel.darkThemeEnabled.observeAsState()
    val viewModel: ThemeViewModel = viewModel()
    val isDarkMode by viewModel.darkThemeEnabled.observeAsState(false)

    isDarkModeEnabled?.let {
        AppTheme(it) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            AccountText()
            ProfileCardUI()
            DarkModeSwitch(isDarkMode) { darkMode ->
                viewModel.toggleTheme()
            }
            Notification()
            GeneralOptionsUI()
            ChangePasswordUI()
            LogoutUI()
        }
    }
    }
}




@Composable
fun AppTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = if (darkTheme) darkColors() else lightColors(),
        typography = MaterialTheme.typography,
        shapes = Shapes,
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)) {
                content()
            }
        }}
    )
}


@Composable
fun AccountText() {
    Text(
        text = "Account",
        fontFamily = Poppins,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    )
}