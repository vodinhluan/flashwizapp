package com.example.flashwiz_fe.data.remote.card

import com.example.flashwiz_fe.data.model.Card

interface RemoteCardDataSource {
    suspend fun getAllCards(): List<Card>
    suspend fun saveCard(frontText: String, backText: String): Card
}