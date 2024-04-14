package com.example.flashwiz_fe.presentation.screen

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
import com.example.flashwiz_fe.R
import com.example.flashwiz_fe.ui.theme.LightPrimaryColor
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.Shapes

@Composable
fun ChangePasswordUI() {
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
                onDismiss = { showChangePasswordDialog = false },
                onSubmit = { oldPassword, newPassword, confirmPassword ->
                    if (newPassword != confirmPassword) {
                        snackbarMessage = "Passwords do not match"
                    } else {
                        // Assume a function to change password here
                        showChangePasswordDialog = false
                        snackbarMessage = "Password successfully changed"
                    }
                }
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
fun ChangePasswordDialog(onDismiss: () -> Unit, onSubmit: (String, String, String) -> Unit) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Change Password") },
        text = {
            Column {
                PasswordInput("Old Password", oldPassword) { oldPassword = it }
                PasswordInput("New Password", newPassword) { newPassword = it }
                PasswordInput("Confirm New Password", confirmPassword) { confirmPassword = it }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (newPassword != confirmPassword) {
                        onSubmit(oldPassword, newPassword, "Passwords do not match")
                    } else {
                        onSubmit(oldPassword, newPassword, confirmPassword)
                    }
                }
            ) {
                Text("Change")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
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
