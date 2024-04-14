package com.example.flashwiz_fe.presentation.screen.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.R


import com.example.flashwiz_fe.presentation.components.login.NavDestinationHelper
import com.example.flashwiz_fe.presentation.screen.NofiticaionScreen
import com.example.flashwiz_fe.presentation.viewmodel.LogoutViewModel
import com.example.flashwiz_fe.ui.theme.DarkColors
import com.example.flashwiz_fe.ui.theme.LightColors
import com.example.flashwiz_fe.ui.theme.LightPrimaryColor
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.PrimaryColor
import com.example.flashwiz_fe.ui.theme.SecondaryColor
import com.example.flashwiz_fe.ui.theme.Shapes



@Composable
fun AccountScreen() {
    var darkTheme by remember { mutableStateOf(false) }
    AppTheme(darkTheme = darkTheme) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            AccountText()
            ProfileCardUI()
            DarkModeSwitch(isDarkMode = darkTheme) { darkTheme = it }
            GeneralOptionsUI()
            ChangePasswordUI()
            LogoutUI()
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


@Composable
fun ProfileCardUI() {
    var showDetails by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        shape = Shapes.large
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = "Check Your Profile",
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Phulebede@gmail.com",
                    fontFamily = Poppins,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Button(
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = { showDetails = !showDetails },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor
                    ),
                    contentPadding = PaddingValues(horizontal = 30.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),
                    shape = Shapes.medium
                ) {
                    Text(
                        text = "View",
                        fontFamily = Poppins,
                        color = SecondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_profile_card_image),
                contentDescription = "",
                modifier = Modifier.height(120.dp)
            )
        }
    }
    if (showDetails) {
        DetailDialog(onDismiss = { showDetails = false })
    }
}


@Composable
fun DetailDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Detailed Information") },
        text = { Text(text = "Here is more detailed information about the user...") },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("Close")
            }
        }
    )
}


@Composable
fun GeneralOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        GeneralSettingItem(
            icon = R.drawable.ic_rounded_notification,
            mainText = "Notifications",
            subText = "Customize notifications",
            onClick = {}
        )
        GeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "More customization",
            subText = "Customize it more to fit your usage",
            onClick = {}
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GeneralSettingItem(icon: Int, mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(LightPrimaryColor)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontFamily = Poppins,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = subText,
                        fontFamily = Poppins,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )

        }
    }
}



@Composable
fun LogoutUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {

        Logout(
            mainText = "LOGOUT",
            onClick = {}
        )
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Logout(mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = mainText,
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DarkModeSwitch(isDarkMode: Boolean, onToggleDarkMode: (Boolean) -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleDarkMode(!isDarkMode) }
                .padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode",
                fontFamily = Poppins,
                color = MaterialTheme.colors.onSurface, // Use onSurface for text color to ensure good contrast
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.Nightlight,
                contentDescription = "Toggle Dark Mode",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}
