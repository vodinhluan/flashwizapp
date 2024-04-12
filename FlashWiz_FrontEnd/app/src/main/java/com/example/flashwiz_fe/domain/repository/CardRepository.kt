package com.example.flashwiz_fe.domain.repository

import com.example.flashwiz_fe.domain.model.Card
import retrofit2.Response

interface CardRepository {
    suspend fun saveCard(card: Card): Response<Card>
}


