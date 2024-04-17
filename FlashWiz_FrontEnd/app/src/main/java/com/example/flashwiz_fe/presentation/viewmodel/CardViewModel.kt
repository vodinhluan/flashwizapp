package com.example.flashwiz_fe.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val cardRepository: CardRepository) : ViewModel() {
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    // list card
    private val _cardsLiveData = MutableLiveData<List<CardDetail>>()
    val cardsLiveData: LiveData<List<CardDetail>> = _cardsLiveData
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _flashcardId = MutableStateFlow(1)
    val flashcardId: StateFlow<Int> = _flashcardId

    private val _randomCardLiveData = MutableLiveData<CardDetail?>()
    val randomCardLiveData: LiveData<CardDetail?> = _randomCardLiveData



    fun setFlashcardId(id: Int) {
        _flashcardId.value = id
        Log.d("ID FlashCard is:", "hello FlashCard ID: $id")
    }

    fun saveCard(card: Card, flashcardId: Int) {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Saving card: $card")
                cardRepository.saveCard(card, flashcardId)
                Log.d("CardViewModel", "Card saved successfully")
                _saveSuccess.value = true
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error saving card: $e")
                _saveSuccess.value = false
            }
        }
    }

    suspend fun getCardsByFlashcardId(flashcardId: Int): List<CardDetail> {
        return cardRepository.getCardsByFlashcardId(flashcardId)
    }

    fun deleteCard(cardId: Int) {
        viewModelScope.launch {
            try {
                cardRepository.deleteCard(cardId)
                // Gọi API để lấy danh sách mới sau khi xóa thành công
            } catch (_: Exception) {
                // Xử lý lỗi nếu cần
            }
        }
    }

    fun deleteCardAndUpdateList(
        cardId: Int,
        viewModel: CardViewModel,
        apiService: CardApiService,
        originalCard: List<CardDetail>,
        updateCards: (List<CardDetail>) -> Unit
    ) {
        viewModel.deleteCard(cardId)
        val updatedCard = originalCard.filterNot { it.id == cardId }
        updateCards(updatedCard)
    }

    fun getRandomCardsByFlashcardId(flashcardId: Int) {
        viewModelScope.launch {
            try {
                val allCards = getCardsByFlashcardId(flashcardId)
                Log.d("Test allCards w ID", "All cards: $allCards")

                val shuffledCards = allCards.shuffled()
                _cardsLiveData.value = shuffledCards

                val randomCard = shuffledCards.firstOrNull()
                _randomCardLiveData.value = randomCard
                randomCard?.let {
                    Log.d("CardViewModel", "Random card selected: $randomCard")
                    Log.d("Front Card", "Front selected: ${randomCard.front}")
                    Log.d("Back Card", "Back selected: ${randomCard.back}")

                } ?: run {
                    Log.d("CardViewModel", "No card selected")
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Failed to fetch random cards: ${e.message}"
            }
        }
    }
}