package com.example.flashwiz_fe.presentation.state

data class CardState(
    val frontText: String = "",
    val backText: String = "",
    val isSaveSuccess: Boolean = false
)
