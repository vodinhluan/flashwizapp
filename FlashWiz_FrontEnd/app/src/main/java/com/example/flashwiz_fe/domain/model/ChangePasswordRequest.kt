package com.example.flashwiz_fe.domain.model

data class ChangePasswordRequest(
    val email: String,
    val oldPassword: String,
    val newPassword: String
)
