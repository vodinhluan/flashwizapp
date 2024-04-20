package com.example.flashwiz_fe.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.presentation.state.EnumReviewCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val cardRepository: CardRepository) : ViewModel() {
    private var initialFlashcardId: Int? = null

    // card state
    private val _cardState = MutableLiveData<EnumReviewCard>(EnumReviewCard.FRONT)
    val cardState: LiveData<EnumReviewCard> = _cardState
    val currentCard: MutableState<CardDetail?> = mutableStateOf(null)

    // save state
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    // List card
    private val _cardsLiveData = MutableLiveData<List<CardDetail>>()
    val cardsLiveData: LiveData<List<CardDetail>> = _cardsLiveData
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    // id card
    private val _flashcardId = MutableStateFlow(1)
    val flashcardId: StateFlow<Int> = _flashcardId

    // random card
    private var _randomCardsLiveData = MutableLiveData<List<CardDetail>>()
    val randomCardsLiveData: LiveData<List<CardDetail>> = _randomCardsLiveData

    // Rating
    private val _currentRating = MutableStateFlow("new")
    val currentRating: StateFlow<String> = _currentRating

    // Các danh sách riêng biệt cho mỗi loại rating
    private var newRatingList: MutableList<CardDetail> = mutableListOf()
    private var failRatingList: MutableList<CardDetail> = mutableListOf()
    private var hardRatingList: MutableList<CardDetail> = mutableListOf()
    private var goodRatingList: MutableList<CardDetail> = mutableListOf()
    private var easyRatingList: MutableList<CardDetail> = mutableListOf()



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
            } catch (_: Exception) {
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

    // *** QUAN TRỌNG ***
    // ** RANDOM CARD **

    fun setInitialFlashcardId(id: Int) {
        initialFlashcardId = id
    }
    fun getRandomCardsByFlashcardId(flashcardId: Int) {
        viewModelScope.launch {
            try {
                val allCards = getCardsByFlashcardId(flashcardId)
                Log.d("ID FlashCard is:", "please FlashCard ID: $flashcardId")
                Log.d("Test allCards w ID", "All cards: $allCards")
                val shuffledCards = allCards.shuffled()
                _cardsLiveData.value = shuffledCards

                val randomCard = shuffledCards.firstOrNull()
                _randomCardsLiveData.value = shuffledCards
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
    fun logCardRatings() {
        Log.d("CardRatings", "(new: ${newRatingList.size}, fail: ${failRatingList.size}, hard: ${hardRatingList.size}, good: ${goodRatingList.size}, easy: ${easyRatingList.size})")
    }

    fun setCurrentRating(rating: String) {
        _currentRating.value = rating
        Log.d("Set Rating is:", "Rating value: $rating")
    }

    fun updateCardRatingInViewModelScope(cardId: Int, newRating: String) {
        viewModelScope.launch {
            updateCardRating(cardId, newRating)
        }
    }
    suspend fun updateCardRating(cardId: Int, newRating: String) {
        cardRepository.updateCardRating(cardId, newRating)
    }

    fun updateCardList(rating: String, card: CardDetail) {
        // Always remove the card from all lists regardless
        newRatingList.remove(card)
        failRatingList.remove(card)
        hardRatingList.remove(card)
        goodRatingList.remove(card)
        easyRatingList.remove(card)

        // Add the card to the new rating list
        when (rating) {
            "new" -> newRatingList.add(card)
            "fail" -> failRatingList.add(card)
            "hard" -> hardRatingList.add(card)
            "good" -> goodRatingList.add(card)
            "easy" -> easyRatingList.add(card)
        }

        onRatingSubmitted()    }

    fun onCardFlipped() {
        _cardState.value = EnumReviewCard.BACK
    }

    fun onRatingSubmitted() {
        _cardState.value = EnumReviewCard.FRONT
        initialFlashcardId?.let { getRandomCardsByFlashcardId(it) }
    }

}
