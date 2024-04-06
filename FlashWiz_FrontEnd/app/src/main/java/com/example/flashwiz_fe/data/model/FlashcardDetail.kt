package com.example.flashwiz_fe.data.model

data class FlashcardDetail(
    val id: Int,
    val name: String,
    val descriptions: String,
    val userId: Int,
    val folderId: Int
)