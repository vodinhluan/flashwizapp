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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
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
import com.example.flashwiz_fe.presentation.viewmodel.RegisterViewModel
import com.example.flashwiz_fe.ui.theme.blue
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.darkGray
import com.example.flashwiz_fe.ui.theme.white
import com.example.flashwiz_fe.ui.theme.whiteGray

@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    NavDestinationHelper(
        shouldNavigate = {
            registerViewModel.registerState.isSuccessfullyRegistered
        }
    ) {
        onRegisterSuccessNavigation()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ){
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
        RegisterContainer(
            nameValue ={
                registerViewModel.registerState.nameInput
            },
            emailValue = {
                registerViewModel.registerState.emailInput
            },
            passwordValue = {
                registerViewModel.registerState.passwordInput
            },
            passwordRepeatedValue = {
                registerViewModel.registerState.passwordRepeatedInput
            },
            buttonEnabled = {
                registerViewModel.registerState.isInputValid
            },
            onNameChanged = registerViewModel::onNameInputChange,
            onEmailChanged = registerViewModel::onEmailInputChange,
            onPasswordChanged = registerViewModel::onPasswordInputChange,
            onPasswordRepeatedChanged = registerViewModel::onPasswordRepeatedInputChange,
            onButtonClick = registerViewModel::onRegisterClick,
            isPasswordShown = {
                registerViewModel.registerState.isPasswordShown
            },
            isPasswordRepeatedShown = {
                registerViewModel.registerState.isPasswordRepeatedShown
            },
            onTrailingPasswordIconClick = {
                registerViewModel.onToggleVisualTransformationPassword()
            },
            onTrailingPasswordRepeatedIconClick = {
                registerViewModel.onToggleVisualTransformationPasswordRepeated()
            },
            errorHint = {
                registerViewModel.registerState.errorMessageInput
            },
            isLoading = {
                registerViewModel.registerState.isLoading
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
        ){
            Text(
                "Đã có tài khoản?",
                style = MaterialTheme.typography.body2,
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToLoginScreen()
                    },
                text = "Đăng nhập",
                color = blue,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun RegisterContainer(
    nameValue: () ->String,
    emailValue:() -> String,
    passwordValue:() -> String,
    passwordRepeatedValue:() -> String,
    buttonEnabled:() -> Boolean,
    onNameChanged:(String)->Unit,
    onEmailChanged:(String)->Unit,
    onPasswordChanged:(String)->Unit,
    onPasswordRepeatedChanged:(String)->Unit,
    onButtonClick:()->Unit,
    isPasswordShown: ()->Boolean,
    isPasswordRepeatedShown: ()->Boolean,
    onTrailingPasswordIconClick: ()->Unit,
    onTrailingPasswordRepeatedIconClick: ()->Unit,
    errorHint:() -> String?,
    isLoading:() -> Boolean,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Text(
//            text = "Đăng ký",
//            color = gray,
//            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.SemiBold)
//        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Your Name",
            hint = "John Dean",
            leadingIcon = Icons.Default.AccountCircle,
            textValue = nameValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onNameChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "flashwizex@gmail.com",
            leadingIcon = Icons.Default.Email,
            textValue = emailValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Password",
            hint = "Enter Password",
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onPasswordChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if(isPasswordShown()){
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            }
        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Confirm Password",
            hint = "Confirm Password",
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordRepeatedValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onPasswordRepeatedChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if(isPasswordRepeatedShown()){
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = {
                onTrailingPasswordRepeatedIconClick()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            AuthButton(
                text = "Register",
                backgroundColor = blue,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                onButtonClick = onButtonClick,
                isLoading = isLoading()
            )
            Text(
                errorHint() ?: "",
                style = MaterialTheme.typography.body2
            )
        }
    }
}