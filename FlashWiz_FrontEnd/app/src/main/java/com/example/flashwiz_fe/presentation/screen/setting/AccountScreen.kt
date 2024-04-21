package com.example.flashwiz_fe.presentation.screen.setting


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashwiz_fe.presentation.components.login.NavDestinationHelper
import com.example.flashwiz_fe.presentation.components.setting.GeneralOptionsUI
import com.example.flashwiz_fe.presentation.components.setting.LogoutUI
import com.example.flashwiz_fe.presentation.components.setting.ProfileCardUI
import com.example.flashwiz_fe.presentation.viewmodel.LogoutViewModel
import com.example.flashwiz_fe.ui.theme.DarkColors
import com.example.flashwiz_fe.ui.theme.LightColors
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.Shapes
import com.example.flashwiz_fe.util.ScreenRoutes


@Composable
fun AccountScreen(
    logoutViewModel: LogoutViewModel = hiltViewModel(),
    navController: NavController
) {
    var darkTheme by remember { mutableStateOf(false) }
    AppTheme(darkTheme = darkTheme) {
        NavDestinationHelper(
            shouldNavigate = {
                logoutViewModel.logoutState.isSuccessfullyLoggedOut
            },
            destination = {
                navController.navigate(ScreenRoutes.LoginScreen.route) {
                    popUpTo(0)
                }
            }
        )
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            AccountText()
            ProfileCardUI()
//            DarkModeSwitch(isDarkMode = darkTheme) { darkTheme = it } Phu Le
            GeneralOptionsUI()
            ChangePasswordUI()
            LogoutUI(
                onLogoutButtonClick = logoutViewModel::logoutClick
            )
            NofiticaionScreen()
        }

    }
}



@Composable
fun AppTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = Shapes,
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)) {
                content()
            }
        }
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