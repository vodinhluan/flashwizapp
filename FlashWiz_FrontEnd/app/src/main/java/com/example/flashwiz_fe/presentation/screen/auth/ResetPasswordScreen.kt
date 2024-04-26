package com.example.flashwiz_fe.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import com.example.flashwiz_fe.presentation.viewmodel.ResetPasswordViewModel
import com.example.flashwiz_fe.ui.theme.blue
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.darkGray
import com.example.flashwiz_fe.ui.theme.white
import com.example.flashwiz_fe.ui.theme.whiteGray

@Composable
fun ResetPasswordScreen(
    onChangePasswordSuccessNavigation:() -> Unit,
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
) {
    NavDestinationHelper(
        shouldNavigate = {
            resetPasswordViewModel.resetPasswordState.isSuccessfullyResetPassword
        },
        destination = {
            onChangePasswordSuccessNavigation()
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
        ResetPasswordContainer(
            newPasswordValue = {
                resetPasswordViewModel.resetPasswordState.newPasswordInput
            },
            newPasswordRepeatedValue = {
                resetPasswordViewModel.resetPasswordState.newPasswordRepeatedInput
            },
            buttonEnabled = {
                resetPasswordViewModel.resetPasswordState.isInputValid
            },
            onNewPasswordChanged = resetPasswordViewModel::onNewPasswordChange,
            onNewPasswordRepeatedChanged = resetPasswordViewModel::onNewPasswordRepeatedInputChange,
            onResetPasswordButtonClick = resetPasswordViewModel::onChangePasswordClick,
            isNewPasswordShown = {
                resetPasswordViewModel.resetPasswordState.isNewPasswordShown
            },
            isNewPasswordRepeatedShown = {
                resetPasswordViewModel.resetPasswordState.isNewPasswordRepeatedShown
            },
            onTrailingNewPasswordIconClick = {
                resetPasswordViewModel.onToggleVisualTransformationNewPassword()
            },
            onTrailingNewPasswordRepeatedIconClick = {
                resetPasswordViewModel.onToggleVisualTransformationNewPasswordRepeated()
            },
            errorHint = {
                resetPasswordViewModel.resetPasswordState.errorMessageInput
            },
            isLoading = {
                resetPasswordViewModel.resetPasswordState.isLoading
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
    }
}
@Composable
fun ResetPasswordContainer(
    newPasswordValue:()-> String,
    newPasswordRepeatedValue:() -> String,
    onNewPasswordChanged:(String) -> Unit,
    onNewPasswordRepeatedChanged:(String)->Unit,
    onResetPasswordButtonClick:()->Unit,
    isNewPasswordShown:()->Boolean,
    isNewPasswordRepeatedShown: ()->Boolean,
    buttonEnabled:() -> Boolean,
    errorHint:()->String?,
    isLoading:()->Boolean,
    onTrailingNewPasswordIconClick: ()->Unit,
    onTrailingNewPasswordRepeatedIconClick: ()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Password Text Entry
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = "Password",
            hint = "Enter Password",
            leadingIcon = Icons.Default.VpnKey,
            textValue = newPasswordValue(),
            textColor = darkGray,
            cursorColor = brightBlue,
            onValueChanged = onNewPasswordChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isNewPasswordShown()) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = { onTrailingNewPasswordIconClick() }
        )

        // Confirm Password Text Entry
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = "Confirm Password",
            hint = "Confirm Password",
            leadingIcon = Icons.Default.VpnKey,
            textValue = newPasswordRepeatedValue(),
            textColor = darkGray,  // Đã thay đổi màu sắc từ gray thành darkGray để phù hợp hơn
            cursorColor = brightBlue,
            onValueChanged = onNewPasswordRepeatedChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isNewPasswordRepeatedShown()) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = { onTrailingNewPasswordRepeatedIconClick() }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthButton(
                text = "Change Password",
                backgroundColor = blue,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                isLoading = isLoading(),
                onButtonClick = onResetPasswordButtonClick,
            )
            Text(
                errorHint() ?: "",
                style = MaterialTheme.typography.body2

            )

        }

    }
}