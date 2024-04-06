package com.example.flashwiz_fe.data.remote.card

import com.example.flashwiz_fe.data.model.Card
import com.example.flashwiz_fe.domain.repository.CardRepository

class CardRepositoryImpl(private val remoteDataSource: RemoteCardDataSource) : CardRepository {
    override suspend fun getAllCards(): List<Card> = remoteDataSource.getAllCards()
    override suspend fun saveCard(frontText: String, backText: String):
            Card = remoteDataSource.saveCard(frontText, backText)
}