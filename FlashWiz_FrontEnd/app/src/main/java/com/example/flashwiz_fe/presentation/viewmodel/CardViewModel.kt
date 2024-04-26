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
    private val _selectedCard = MutableLiveData<CardDetail>()
    val selectedCard: LiveData<CardDetail> = _selectedCard
    private val _updatedCard = MutableLiveData<CardDetail>()
    val updatedCard: LiveData<CardDetail> = _updatedCard

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

    // Rating
    private val _currentRating = MutableStateFlow("new")
    val currentRating: StateFlow<String> = _currentRating // ?

    // XỬ LÝ PHẦN RANDOM CARD QUAN TRỌNG
    private var ratedCards = 0
    var unratedCards = 0

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

                // Đặt số card đã rating về 0 và số card chưa rating về tổng số card
                ratedCards = 0
                unratedCards = allCards.size

                Log.d("Test Rated Cards", "Rated Cards: $ratedCards")
                Log.d("Test Unrated Cards", "Unrated Cards: $unratedCards")

                // Phân loại card theo rating
                newRatingList.clear()
                failRatingList.clear()
                hardRatingList.clear()
                goodRatingList.clear()
                easyRatingList.clear()

                allCards.forEach { card ->
                    when (card.rating) {
                        "new" -> newRatingList.add(card)
                        "fail" -> failRatingList.add(card)
                        "hard" -> hardRatingList.add(card)
                        "good" -> goodRatingList.add(card)
                        "easy" -> easyRatingList.add(card)
                    }
                }

                // Random card dựa trên phân loại
                if (newRatingList.isNotEmpty()) {
                    // Random ra new trước nếu còn
                    val randomCard = randomCardFromList(newRatingList)
                    Log.d("Test Rating: ", "New Rating List: $newRatingList")
                    Log.d("Test Rating: ", "Random card from New Rating List: $randomCard.")

                } else if (failRatingList.isNotEmpty()) {
                    // Nếu hết New, Còn Fail không? -> random ra fail trước
                    val randomCard = randomCardFromList(failRatingList)
                    Log.d("Test Rating: ", "Fail Rating List: $failRatingList")
                    Log.d("Test Rating: ", "Random card from Fail Rating List: $randomCard")


                } else if (hardRatingList.isNotEmpty()) {
                    // Nếu hết New, Fail. Còn Hard không? -> random ra hard trước
                    val randomCard = randomCardFromList(hardRatingList)
                    Log.d("Test Rating: ", "Hard Rating List: $hardRatingList")
                    Log.d("Test Rating: ", "Random card from Hard Rating List: $randomCard")


                } else if (goodRatingList.isNotEmpty()) {
                    // Nếu hết New, Fail, Hard. Còn Good không? -> random ra good trước
                    val randomCard = randomCardFromList(goodRatingList)
                    Log.d("Test Rating: ", "Good Rating List: $goodRatingList")
                    Log.d("Test Rating: ", "Random card from Good Rating List: $randomCard")

                } else {
                    // Nếu tất cả là Easy -> random ngẫu nhiên
                    val randomCard = randomCardFromList(easyRatingList)
                    Log.d("Test Rating Phân ra", "Good Rating List: $easyRatingList")
                    Log.d("Test Rating: ", "Random card from Easy Rating List: $randomCard")

                }

            } catch (e: Exception) {
                _errorLiveData.value = "Failed to fetch random cards: ${e.message}"
            }
        }
    }

    private fun randomCardFromList(cardList: List<CardDetail>) {
        val randomCard = cardList.random()
        _cardsLiveData.value = listOf(randomCard)
    }

    fun removeCurrentCardFromRatingList() {
        val currentCard = cardsLiveData.value?.firstOrNull() ?: return
        val ratingList = when (currentCard.rating) {
            "new" -> newRatingList
            "fail" -> failRatingList
            "hard" -> hardRatingList
            "good" -> goodRatingList
            "easy" -> easyRatingList
            else -> return
        }
        Log.d("Test Rating", "Current card to remove: $currentCard")
        val removed = ratingList.remove(currentCard)
        Log.d("Test Rating", "Card removed from rating list: $removed")
        Log.d("Test Rating", "Rating list removed: $ratingList")

        // Random card mới từ danh sách rating
        if (ratingList.isEmpty()) {
            // Nếu danh sách rating hiện tại rỗng, chuyển sang duyệt danh sách rating khác
            val nextRatingList = getNextRatingList(currentCard.rating)
            if (nextRatingList.isNotEmpty()) {
                randomCardFromList(nextRatingList)
                Log.d("Test Rating: ", "Next Rating List: $nextRatingList")
            } else {
                Log.d("ReviewCardScreen", "Bạn đã học hết Card. Xin chúc mừng!")
            }
        } else {
            randomCardFromList(ratingList)
            Log.d("Test Rating: ", "Random card from $currentCard.rating Rating List: $ratingList")
        }

        _cardState.value = EnumReviewCard.FRONT
    }

    private fun getNextRatingList(currentRating: String): List<CardDetail> {
        val ratingOrder = listOf("fail", "hard", "good", "easy")
        val startIndex = ratingOrder.indexOf(currentRating)
        for (i in startIndex + 1 until ratingOrder.size) {
            when (ratingOrder[i]) {
                "fail" -> if (failRatingList.isNotEmpty()) return failRatingList
                "hard" -> if (hardRatingList.isNotEmpty()) return hardRatingList
                "good" -> if (goodRatingList.isNotEmpty()) return goodRatingList
                "easy" -> if (easyRatingList.isNotEmpty()) return easyRatingList
            }
        }
        return emptyList()
    }

    // ** END **

    fun setCurrentRating(rating: String) {
        _currentRating.value = rating
        Log.d("Set Rating is:", "Rating value: $rating")
    }

    fun updateCardRatingInViewModelScope(cardId: Int, newRating: String) {
        viewModelScope.launch {
            updateCardRating(cardId, newRating)
        }
    }
    private suspend fun updateCardRating(cardId: Int, newRating: String) {
        cardRepository.updateCardRating(cardId, newRating)
        Log.d("Test New Rating Cards", "New Rating Updated!!!: $newRating")
    }

    fun onCardFlipped() {
        _cardState.value = EnumReviewCard.BACK
    }



}