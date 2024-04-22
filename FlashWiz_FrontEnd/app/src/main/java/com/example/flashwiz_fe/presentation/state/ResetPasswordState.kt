package com.example.flashwiz_fe.presentation.state

data class ResetPasswordState(
    val newPasswordInput:String = "",
    val newPasswordRepeatedInput:String = "",
    val isInputValid:Boolean = false,
    val isNewPasswordShown:Boolean = false,
    val isNewPasswordRepeatedShown:Boolean = false,
    val errorMessageInput:String? = null,
    val isLoading:Boolean = false,
    val isSuccessfullyResetPassword:Boolean = false,
    val errorMessageResetPasswordProcess:String? = null
)
