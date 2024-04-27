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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.presentation.components.login.AuthButton
import com.example.flashwiz_fe.presentation.components.login.BubbleAnimation
import com.example.flashwiz_fe.presentation.components.login.HeaderBackground
import com.example.flashwiz_fe.presentation.components.login.NavDestinationHelper
import com.example.flashwiz_fe.presentation.components.login.TextEntryModule
import com.example.flashwiz_fe.presentation.viewmodel.ForgotPasswordViewModel
import com.example.flashwiz_fe.ui.theme.blue
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.darkGray
import com.example.flashwiz_fe.ui.theme.white
import com.example.flashwiz_fe.ui.theme.whiteGray


@Composable
fun ForgotPasswordScreen(
    onForgotPasswordSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    NavDestinationHelper(
        shouldNavigate = {
            forgotPasswordViewModel.forgotPasswordState.isSuccessfullySendEmail
        },
        destination = {
            onForgotPasswordSuccessNavigation()
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
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = blue,
                rightColor = brightBlue,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "FlashWiz",
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily.Cursive,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        ForgotPasswordContainer(
            emailValue = {
                forgotPasswordViewModel.forgotPasswordState.emailInput
            },
            buttonEnabled = {
                forgotPasswordViewModel.forgotPasswordState.isInputValid
            },
            onEmailChanged = forgotPasswordViewModel::onEmailInputChange,
            onLoginButtonClick = forgotPasswordViewModel::onSendEmailClick,
            errorHint = {
                forgotPasswordViewModel.forgotPasswordState.errorMessageInput
            },
            isLoading = {
                forgotPasswordViewModel.forgotPasswordState.isLoading
            },
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
                "<- Quay lại trang đăng nhập",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToLoginScreen()
                    },
                color = blue,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }

}
@Composable
fun ForgotPasswordContainer(
    emailValue:() -> String,
    buttonEnabled:() -> Boolean,
    onEmailChanged:(String) -> Unit,
    onLoginButtonClick:()->Unit,
    errorHint:()->String?,
    isLoading:()->Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "your email here",
            textValue = emailValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            leadingIcon = Icons.Default.Email
        )
        AuthButton(
            text = "Send",
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
            errorHint() ?: "",
            style = MaterialTheme.typography.body2

        )

    }
}




//    NavDestinationHelper(
//        shouldNavigate = {
//            forgotPasswordViewModel.forgotPasswordState.isSuccessfullySendEmail
//        },
//        destination = {
//            onSendEmailSuccessNavigation()
//        }
//    )