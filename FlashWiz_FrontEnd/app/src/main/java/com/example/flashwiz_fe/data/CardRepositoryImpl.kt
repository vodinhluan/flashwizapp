package com.example.flashwiz_fe.data

import com.example.flashwiz_fe.data.remote.ApiService
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.repository.CardRepository
import retrofit2.Response

class CardRepositoryImpl(private val cardApi: ApiService) : CardRepository {
    override suspend fun saveCard(card: Card): Response<Card> {
        return cardApi.saveCard(card)
    }
}