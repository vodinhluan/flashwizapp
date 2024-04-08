package com.example.flashwiz_fe.domain.use_case

import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.repository.CardRepository

interface CardUseCase {
    suspend fun saveCard(card: Card)
}

class CardUseCaseImpl(private val cardRepository: CardRepository) : CardUseCase {
    override suspend fun saveCard(card: Card) {
        cardRepository.saveCard(card)
    }
}


