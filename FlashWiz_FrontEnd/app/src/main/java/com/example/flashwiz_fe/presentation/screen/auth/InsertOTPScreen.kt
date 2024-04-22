package com.example.flashwiz_fe.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashwiz_fe.presentation.components.login.AuthButton
import com.example.flashwiz_fe.presentation.components.login.BubbleAnimation
import com.example.flashwiz_fe.presentation.components.login.HeaderBackground
import com.example.flashwiz_fe.presentation.components.login.NavDestinationHelper
import com.example.flashwiz_fe.presentation.components.login.TextEntryModule
import com.example.flashwiz_fe.presentation.viewmodel.InsertOTPViewModel
import com.example.flashwiz_fe.ui.theme.blue
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white
import com.example.flashwiz_fe.ui.theme.whiteGray

@Composable
fun InsertOTPScreen(
    onVerifiedOTPSuccessNavigation:() -> Unit,
    insertOTPViewModel: InsertOTPViewModel = hiltViewModel()
) {
    NavDestinationHelper(
        shouldNavigate = {
            insertOTPViewModel.insertOTPState.isSuccessfullyVerifyOTP
        },
        destination = {
            onVerifiedOTPSuccessNavigation()
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
        InsertOTPContainer(
            otpValue = {
                insertOTPViewModel.insertOTPState.otpInput
            },
            onOTPChanged = insertOTPViewModel::onOTPInputChange,
            errorHint =  {
                insertOTPViewModel.insertOTPState.errorMessageInput
            },
            isLoading = {
                insertOTPViewModel.insertOTPState.isLoading
            },
            onVerifiedOTPClick = insertOTPViewModel::onVerifiedOTPClick,
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        BubbleAnimation(
            bubbleColor1 = brightBlue,
            bubbleColor2 = blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
        )
    }
}
@Composable
fun InsertOTPContainer(
    onOTPChanged:(String) ->Unit,
    otpValue:() -> String,
    isLoading:()->Boolean,
    errorHint:()->String?,
    onVerifiedOTPClick:() -> Unit,
    modifier: Modifier

){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextEntryModule(
            description = "Enter OTP",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 5.dp),
            hint = "123456",
            leadingIcon = Icons.Default.Lock,
            textValue = otpValue(),
            textColor = Color.Black,
            cursorColor = brightBlue,
            onValueChanged = onOTPChanged,
            visualTransformation = VisualTransformation.None,
            trailingIcon = null,
            keyboardType = KeyboardType.Number,
            onTrailingIconClick = {},
        )
        AuthButton(
            text = "Verify OTP",
            backgroundColor = blue,
            contentColor = white,
            onButtonClick = onVerifiedOTPClick,
            isLoading = isLoading(),
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp)
                .shadow(5.dp, RoundedCornerShape(25.dp)),
        )
        Text(
            errorHint() ?: "",
            style = MaterialTheme.typography.body2
        )

    }
}