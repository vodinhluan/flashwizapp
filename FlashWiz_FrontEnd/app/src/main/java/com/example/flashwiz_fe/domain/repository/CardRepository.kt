package com.example.flashwiz_fe.domain.repository

import com.example.flashwiz_fe.domain.model.Card

interface CardRepository {
    suspend fun getAllCards(): List<Card>
    suspend fun saveCard(frontText: String, backText: String): Card
}