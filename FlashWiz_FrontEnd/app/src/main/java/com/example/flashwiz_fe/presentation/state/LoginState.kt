package com.example.flashwiz_fe.presentation.state

data class LoginState(
    val userId:String = "",
    val emailInput:String = "",
    val passwordInput:String = "",
    val isInputValid:Boolean = false,
    val isPasswordShown:Boolean = false,
    val errorMessageInput:String? = null,
    val isLoading:Boolean = false,
    val isSuccessfullyLoggedIn:Boolean = false,
    val errorMessageLoginProcess:String? = null
)

