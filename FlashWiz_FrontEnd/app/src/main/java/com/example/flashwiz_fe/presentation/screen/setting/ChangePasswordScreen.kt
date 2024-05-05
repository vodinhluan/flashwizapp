package com.example.flashwiz_fe.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.R
import com.example.flashwiz_fe.presentation.viewmodel.ChangePasswordViewModel
import com.example.flashwiz_fe.ui.theme.LightPrimaryColor
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.Shapes

@Composable
fun ChangePasswordUI(changePasswordViewModel: ChangePasswordViewModel = hiltViewModel()) {
    var showChangePasswordDialog by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(horizontal = 14.dp)
        .padding(top = 10.dp)) {
        ChangePassword(
            icon = R.drawable.ic_changepassword,
            mainText = "Change Password",
            subText = "Change your password here",
            onClick = { showChangePasswordDialog = true }
        )
        if (showChangePasswordDialog) {
            ChangePasswordDialog(
                oldPasswordValue = {
                    changePasswordViewModel.changePasswordState.oldPasswordInput
                },
                newPasswordValue = {
                    changePasswordViewModel.changePasswordState.newPasswordInput
                },
                onOldPasswordChanged = changePasswordViewModel::onOldPasswordInputChange,
                onNewPasswordChanged = changePasswordViewModel::onNewPasswordInputChange,

                onChangePasswordButtonClick = changePasswordViewModel::onChangePasswordClick,
                onDismiss = { showChangePasswordDialog = false },
                onChangeSuccess = {
                    showChangePasswordDialog = false}
            )
        }
    }

    if (snackbarMessage.isNotEmpty()) {
        Snackbar(
            modifier = Modifier.padding(8.dp),
            action = {
                Button(onClick = { snackbarMessage = "" }) {
                    Text("OK", color = Color.White)
                }
            }
        ) {
            Text(snackbarMessage)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChangePassword(icon: Int, mainText: String, subText: String, onClick: () -> Unit) {
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
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(Shapes.medium)
                    .background(LightPrimaryColor),
                contentAlignment = Alignment.Center // Ensure the icon is centered within the box
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Change Password Icon",
                    tint = Color.Unspecified, // Maintain the icon's original colors
                    modifier = Modifier.size(18.dp) // Adjust the size inside the Box
                )
            }

            Spacer(modifier = Modifier.width(14.dp)) // Maintain the space between the icon and the text

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp) // Adjust text spacing for readability
            ) {
                Text(
                    text = mainText,
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 16.sp, // Adjusted for visibility
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = subText,
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp, // Adjusted for subtext
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Push the right arrow to the end of the row

            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "Navigate Icon",
                modifier = Modifier.size(20.dp) // Adjust size for uniformity
            )
        }
    }
}

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    onChangeSuccess: () -> Unit,
    onChangePasswordButtonClick: () -> Unit,
    oldPasswordValue: () -> String,
    newPasswordValue: () -> String,
    onOldPasswordChanged: (String) -> Unit,
    onNewPasswordChanged: (String) -> Unit,
    )
{
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var snackbarMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Đổi Mật khẩu") },
        text = {
            Column {
                PasswordInput(
                    label = "Mật khẩu cũ",
                    value = oldPasswordValue(),
                    onValueChange = onOldPasswordChanged
                )
                PasswordInput(
                    label = "Mật khẩu mới",
                    value = newPasswordValue(),
                    onValueChange = onNewPasswordChanged
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onChangePasswordButtonClick() }
            ) {
                Text("Đổi")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Hủy")
            }
        }
    )
}

@Composable
fun PasswordInput(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )
}