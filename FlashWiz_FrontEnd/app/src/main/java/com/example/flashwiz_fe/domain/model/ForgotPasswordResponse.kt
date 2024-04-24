package com.example.flashwiz_fe.domain.model

data class ForgotPasswordResponse(
    val otp: String,
    val email: String
)