package com.example.flashwiz_fe.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.model.Card
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.presentation.state.CardState
import kotlinx.coroutines.launch

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {
    private val _cardState = MutableLiveData<CardState>()
    val cardState: LiveData<CardState> = _cardState

    fun saveCard(frontText: String, backText: String) {
        _cardState.value = CardState.Loading
        viewModelScope.launch {
            try {
                // Thực hiện lưu thẻ vào repository
                val card = cardRepository.saveCard(frontText, backText)
                _cardState.value = CardState.Loaded(card.front, card.back)
            } catch (e: Exception) {
                _cardState.value = CardState.Error
            }
        }
    }
}
