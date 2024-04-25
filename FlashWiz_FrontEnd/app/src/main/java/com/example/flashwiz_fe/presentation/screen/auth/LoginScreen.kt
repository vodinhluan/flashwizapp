package com.example.flashwiz_fe.presentation.screen.auth


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.presentation.components.login.AuthButton
import com.example.flashwiz_fe.presentation.components.login.BubbleAnimation
import com.example.flashwiz_fe.presentation.components.login.HeaderBackground
import com.example.flashwiz_fe.presentation.components.login.NavDestinationHelper
import com.example.flashwiz_fe.presentation.components.login.TextEntryModule
import com.example.flashwiz_fe.presentation.viewmodel.LoginViewModel
import com.example.flashwiz_fe.ui.theme.blue
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.darkGray
import com.example.flashwiz_fe.ui.theme.white
import com.example.flashwiz_fe.ui.theme.whiteGray

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToForgotPasswordScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    NavDestinationHelper(
        shouldNavigate = {
            loginViewModel.loginState.isSuccessfullyLoggedIn
        },
        destination = {
            onLoginSuccessNavigation()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = blue, rightColor = brightBlue, modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "FlashWiz",
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily.Cursive,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        LoginContainer(emailValue = {
            loginViewModel.loginState.emailInput
        },
            passwordValue = {
                loginViewModel.loginState.passwordInput
            },
            buttonEnabled = {
                loginViewModel.loginState.isInputValid
            },
            onEmailChanged = loginViewModel::onEmailInputChange,
            onPasswordChanged = loginViewModel::onPasswordInputChange,
            onLoginButtonClick = loginViewModel::onLoginClick,
            isPasswordShown = {
                loginViewModel.loginState.isPasswordShown
            },
            onTrailingPasswordIconClick = loginViewModel::onToggleVisualTransformation,
            errorHint = {
                loginViewModel.loginState.errorMessageInput
            },
            isLoading = {
                loginViewModel.loginState.isLoading
            },
            onNavigateToForgotPasswordScreen = onNavigateToForgotPasswordScreen,
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        BubbleAnimation(
            bubbleColor1 = white,
            bubbleColor2 = brightBlue,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Bạn chưa có tài khoản ?", style = MaterialTheme.typography.body2
            )
            Text(
                "Đăng ký",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToRegisterScreen()
                    },
                color = blue,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Composable
fun LoginContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    buttonEnabled: () -> Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    errorHint:()->String?,
    isLoading:()->Boolean,
    onNavigateToForgotPasswordScreen:() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Đăng nhập",
            color = blue,
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold)
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = "Email address",
            hint = "",
            textValue = emailValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            leadingIcon = Icons.Default.Email
        )
        Column() {
            TextEntryModule(
                modifier = Modifier.fillMaxWidth(),
                description = "Password",
                hint = "",
                textValue = passwordValue(),
                textColor = darkGray,
                cursorColor = brightBlue,
                onValueChanged = onPasswordChanged,
                trailingIcon = Icons.Default.RemoveRedEye,
                onTrailingIconClick = {
                    onTrailingPasswordIconClick()
                },
                leadingIcon = Icons.Default.VpnKey,
                visualTransformation = if (isPasswordShown()) {
                    VisualTransformation.None
                } else PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var rememberMe by remember { mutableStateOf(false) }

                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { isChecked ->
                            rememberMe = isChecked
                        },

                        )
                    Text(
                        "Remember me",
                        modifier = Modifier,
                        color = darkGray,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.body2
                    )
                }
                Text(
                    "Forgot your password?",
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            onNavigateToForgotPasswordScreen()
                        },
                    color = blue,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthButton(
                text = "Login",
                backgroundColor = blue,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                isLoading = isLoading(),
                onButtonClick = onLoginButtonClick,
            )
            Text(
                errorHint() ?: "", style = MaterialTheme.typography.body2

            )

        }

    }
}

