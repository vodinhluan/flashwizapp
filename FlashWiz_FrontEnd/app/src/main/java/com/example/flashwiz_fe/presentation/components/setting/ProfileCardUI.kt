package com.example.flashwiz_fe.presentation.components.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.R
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.PrimaryColor
import com.example.flashwiz_fe.ui.theme.SecondaryColor
import com.example.flashwiz_fe.ui.theme.Shapes

@Composable
fun ProfileCardUI() {
    var showDetails by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val userEmailState = remember { mutableStateOf("") }
    val userNameState = remember {  mutableStateOf("") }
    LaunchedEffect(userEmailState.value, userNameState.value) {
        val userEmail = userPreferences.getUserEmail()
        userEmailState.value = (userEmail ?: "").toString()
        val userName = userPreferences.getUserName()
        userNameState.value = (userName ?: "").toString()
    }
    val userName by remember { derivedStateOf { userNameState.value } }
    val userEmail by remember { derivedStateOf { userEmailState.value } }
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
                    text = " Hello, $userName",
                    fontFamily = Poppins,
                    color = Color.Black,
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
        DetailDialog(
            userEmail = userEmail,
            userName = userName,
            onDismiss = { showDetails = false },
            onChangeName = {
                // Xử lý sự kiện thay đổi tên người dùng ở đây
            }
        )
    }

}

@Composable
fun DetailDialog(userEmail: String, userName: String, onDismiss: () -> Unit, onChangeName: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Thông tin chi tiết người dùng"
            )
        },
        text = {
            Column {
                Text(
                    text = "Tên người dùng: $userName"
                )
                Text(
                    text = "Email người dùng: $userEmail"
                )
            }
        },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onChangeName() }) {
                    Text("Change Name")
                }
                Button(onClick = { onDismiss() }) {
                    Text("Close")
                }
            }
        }
    )
}