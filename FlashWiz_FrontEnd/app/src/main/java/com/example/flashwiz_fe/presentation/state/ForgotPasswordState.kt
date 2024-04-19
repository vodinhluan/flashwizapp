package com.example.flashwiz_fe.presentation.state

data class ForgotPasswordState(
    val emailInput:String = "",
    val isInputValid:Boolean = false,
    val errorMessageInput:String? = null,
    val isLoading:Boolean = false,
    val isSuccessfullySendEmail:Boolean = false,
    val errorMessageSendEmailProcess:String? = null
)
