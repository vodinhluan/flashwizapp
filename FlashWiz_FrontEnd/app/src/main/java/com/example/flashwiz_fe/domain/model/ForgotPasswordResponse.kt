package com.example.flashwiz_fe.domain.model

data class ForgotPasswordResponse(
    val OTP: String,
    val email: String
)