package com.example.flashwiz_fe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.remote.FlashcardApiService
import com.example.flashwiz_fe.domain.model.Flashcard
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import kotlinx.coroutines.launch

class FlashcardViewModel: ViewModel() {
    private val flashcardService = RetrofitInstance.flashcardApiService

    fun addFlashcard(name: String, description: String, folderId: Int, onResult: (Boolean) -> Unit) {
        val flashcard = Flashcard(name = name, descriptions = description, userId = 2, folderId = folderId)
        viewModelScope.launch {
            try {
                val response = flashcardService.saveFlashcard(flashcard, 2, folderId)
                if (response.equals("ok")) {
                    onResult(true)
                } else {
                    // Xử lý trường hợp không thành công
                    onResult(false)
                }
            } catch (e: Exception) {
                // Xử lý lỗi
                onResult(false)
            }
        }
    }

    private fun deleteFlashcard(flashcardId: Int) {
        viewModelScope.launch {
            try {
                flashcardService.deleteFlashcard(flashcardId)
                // Gọi API để lấy danh sách mới sau khi xóa thành công
            } catch (_: Exception) {
                // Xử lý lỗi nếu cần
            }
        }
    }
    fun deleteFlashcardAndUpdateList(flashcardId: Int, viewModel: FlashcardViewModel, apiService: FlashcardApiService, originalFlashcard: List<FlashcardDetail>, updateFlashcards: (List<FlashcardDetail>) -> Unit) {
        viewModel.deleteFlashcard(flashcardId)
        val updatedFlashcard = originalFlashcard.filterNot { it.id == flashcardId }
        updateFlashcards(updatedFlashcard)
    }
}

