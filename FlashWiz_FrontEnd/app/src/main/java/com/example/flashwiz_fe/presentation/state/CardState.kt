package com.example.flashwiz_fe.presentation.state

sealed class CardState {
    object Loading : CardState()
    data class Loaded(val front: String, val back: String) : CardState()
    object Error : CardState()
}