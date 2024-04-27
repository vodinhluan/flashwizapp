package com.example.flashwiz_fe.domain.model

data class TokenResponse(
    val id: Int,
    val accessToken: String,
    val email: String
)