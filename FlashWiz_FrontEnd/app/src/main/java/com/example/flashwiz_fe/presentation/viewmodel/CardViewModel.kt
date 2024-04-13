package com.example.flashwiz_fe.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.data.remote.FlashcardApiService
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.model.FlashcardDetail
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
    fun saveCard(card: Card) {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Saving card: $card")
                cardRepository.saveCard(card)
                Log.d("CardViewModel", "Card saved successfully")
                _saveSuccess.value = true
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error saving card: $e")
                _saveSuccess.value = false
            }
        }
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
    fun deleteCardAndUpdateList(cardId: Int, viewModel: CardViewModel, apiService: CardApiService, originalCard: List<CardDetail>, updateCards: (List<CardDetail>) -> Unit) {
        viewModel.deleteCard(cardId)
        val updatedCard = originalCard.filterNot { it.id == cardId }
        updateCards(updatedCard)
    }
}









