package com.example.flashwiz_fe.domain.repository

import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import retrofit2.Response

interface CardRepository {
    suspend fun saveCard(card: Card, flashcardId: Int): Response<Card>
    suspend fun getCardsByFlashcardId(flashcardId: Int): List<CardDetail>
    suspend fun deleteCard(id: Int): List<CardDetail>
    suspend fun getAllCards(): List<Card>
    suspend fun updateCardRating(cardId: Int, newRating: String): Response<Card>
    suspend fun getCardStatistics(flashcardId: Int): Response<Map<String, Int>>
    suspend fun updateCard(cardId: Int, updatedCard: CardDetail): Response<CardDetail>
    suspend fun getCardById(id: Int): Response<CardDetail>

}