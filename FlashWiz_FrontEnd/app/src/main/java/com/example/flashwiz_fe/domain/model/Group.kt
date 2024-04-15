package com.example.flashwiz_fe.domain.model

data class Group(
    val name: String,
    val groupCode: String,
    val userId: Int,
    val flashcards: List<Flashcard>
)
