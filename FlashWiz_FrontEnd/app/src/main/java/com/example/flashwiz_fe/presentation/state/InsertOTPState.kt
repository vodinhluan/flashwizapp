package com.example.flashwiz_fe.presentation.state

data class InsertOTPState(
    val otpInput:String = "",
    val isInputValid:Boolean = false,
    val errorMessageInput:String? = null,
    val isLoading:Boolean = false,
    val isSuccessfullyVerifyOTP:Boolean = false,
    val errorMessageVerifyOTPProcess:String? = null
)
