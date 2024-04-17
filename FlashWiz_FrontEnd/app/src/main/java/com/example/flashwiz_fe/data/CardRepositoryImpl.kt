package com.example.flashwiz_fe.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flashwiz_fe.data.RetrofitInstance.cardApiService
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.repository.CardRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CardRepositoryImpl(private val cardApiService: CardApiService) : CardRepository {
    override suspend fun saveCard(card: Card, flashcardId: Int): Response<Card> {
        return cardApiService.saveCard(card, flashcardId)
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

}