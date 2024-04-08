package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.model.Flashcard
import com.example.flashwiz_fe.data.model.Folder
import com.example.flashwiz_fe.data.model.FolderDetail
import com.example.flashwiz_fe.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class AddFlashcardViewModel: ViewModel() {
    private val flashcardService = RetrofitInstance.flashcardApiService

    fun addFlashcard(name: String, description: String,folderId: Int, onResult: (Boolean) -> Unit) {
        val flashcard = Flashcard(name = name, descriptions = description, userId = 2,folderId = 3 )
        viewModelScope.launch {
            try {
                val response = flashcardService.saveFlashcard(flashcard, 2,3)
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

