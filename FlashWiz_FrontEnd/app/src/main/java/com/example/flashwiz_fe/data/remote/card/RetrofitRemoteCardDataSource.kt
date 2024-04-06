package com.example.flashwiz_fe.data.remote.card

import com.example.flashwiz_fe.data.model.Card

class RetrofitRemoteCardDataSource(private val cardApi: CardApi) : RemoteCardDataSource {
    override suspend fun getAllCards(): List<Card> = cardApi.getAllCards()
    override suspend fun saveCard(frontText: String, backText: String):
            Card = cardApi.saveCard(Card(0, frontText, backText))
}