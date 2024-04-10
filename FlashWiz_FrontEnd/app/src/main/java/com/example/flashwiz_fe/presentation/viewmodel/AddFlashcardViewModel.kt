package com.example.flashwiz_fe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.domain.model.Flashcard
import kotlinx.coroutines.launch

class AddFlashcardViewModel: ViewModel() {
    private val flashcardService = RetrofitInstance.flashcardApiService

    fun addFlashcard(name: String, description: String,folderId: Int, onResult: (Boolean) -> Unit) {
        val flashcard = Flashcard(name = name, descriptions = description, userId = 2,folderId = folderId )
        viewModelScope.launch {
            try {
                val response = flashcardService.saveFlashcard(flashcard, 2,folderId)
                if (response.equals("ok")) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false) // Xử lý exception
            }
        }
    }
}

