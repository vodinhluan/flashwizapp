package com.example.flashwiz_fe.data

import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.repository.CardRepository
import retrofit2.Response

class CardRepositoryImpl(private val cardApiService: CardApiService) : CardRepository {
    override suspend fun saveCard(card: Card, flashcardId: Int): Response<Card> {
        return cardApiService.saveCard(card,flashcardId)
    }

    override suspend fun getCardsByFlashcardId(flashcardId: Int): List<CardDetail> {
        return cardApiService.getCardsByFlashcardId(flashcardId)
    }

    override suspend fun deleteCard(id: Int): List<CardDetail> {
        return cardApiService.deleteCard(id)
    }

    override suspend fun getAllCards(): List<Card> {
        return cardApiService.getAllCards()
    }

    override suspend fun updateCardRating(cardId: Int, newRating: String): Response<Card> {
        return cardApiService.updateCardRating(cardId, newRating)
    }
    override suspend fun updateCard(cardId: Int, updatedCard: CardDetail): Response<CardDetail> {
        return cardApiService.updateCard(cardId, updatedCard)

    }
    override suspend fun getCardById(id: Int): Response<CardDetail> {
        return cardApiService.getCardById(id)
    }

}