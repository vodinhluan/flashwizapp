package com.example.flashwiz_fe.domain.model

data class Statistic(
    val totalCards: Int,
    val failCount: Int,
    val hardCount: Int,
    val goodCount: Int,
    val easyCount: Int
)