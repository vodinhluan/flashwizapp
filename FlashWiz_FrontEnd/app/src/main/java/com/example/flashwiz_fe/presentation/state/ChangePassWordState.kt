package com.example.flashwiz_fe.presentation.state

data class ChangePassWordState(
    val oldPasswordInput:String = "",
    val newPasswordInput:String = "",
    val isSuccessfullyChangePassword:Boolean = false,
    val isPasswordShown:Boolean = false
)
