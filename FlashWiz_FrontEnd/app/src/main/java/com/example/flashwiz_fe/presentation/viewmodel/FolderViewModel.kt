package com.example.flashwiz_fe.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.RetrofitInstance.cardApiService
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.data.remote.FlashcardApiService
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.model.Folder
import kotlinx.coroutines.launch


class FolderViewModel() : ViewModel() {
    private val folderService = RetrofitInstance.folderApiService
    private val _folders = mutableStateOf<List<FolderDetail>>(emptyList())
    val folders: State<List<FolderDetail>> = _folders
    private val flashcardApiService = RetrofitInstance.flashcardApiService

    private val _flashcardsState = mutableStateOf<List<FlashcardDetail>>(emptyList())
    val flashcardsState: State<List<FlashcardDetail>> = _flashcardsState

    private val _cardsState = mutableStateOf<List<CardDetail>>(emptyList())
    val cardState: MutableState<List<CardDetail>> = _cardsState

    fun getFlashcardsByFolderId(folderId: Int) {
        viewModelScope.launch {
            try {
                val flashcards = flashcardApiService.getFlashcardsByFolderId(folderId)
                _flashcardsState.value = flashcards
                Log.d("FlashCard123456", "DanhSachFlashCard: $_flashcardsState")
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("FolderViewModel", "Error getting flashcards: ${e.message}")
            }
        }
    }


    fun getCardsByFolderId(flashCardId: Int) {
        viewModelScope.launch {
            try {
                val cards = cardApiService.getCardsByFlashcardId(flashCardId)
                _cardsState.value = cards
                Log.d("FlashCard123456", "DanhSach Card: $_cardsState")
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("FolderViewModel", "Error getting cards: ${e.message}")
            }
        }
    }

    fun addFolder(name: String, description: String,userId: Int, onResult: (Boolean) -> Unit) {
        val folder = Folder(name = name, descriptions = description, userId = userId)
        viewModelScope.launch {
            try {
                val response = folderService.saveFolder(folder, userId)
                if (response.equals("ok")) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
    private fun deleteFolder(folderId: Int) {
        viewModelScope.launch {
            try {
                folderService.deleteFolder(folderId)
            } catch (_: Exception) {
            }
        }
    }
    fun deleteFolderAndUpdateList(folderId: Int, viewModel: FolderViewModel, apiService: FolderApiService, originalFolders: List<FolderDetail>, updateFolders: (List<FolderDetail>) -> Unit) {
        viewModel.deleteFolder(folderId)
        val updatedFolders = originalFolders.filterNot { it.id == folderId }
        updateFolders(updatedFolders)
    }

}
