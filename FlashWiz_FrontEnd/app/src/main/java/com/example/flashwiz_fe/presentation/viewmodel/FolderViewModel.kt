package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.Folder
import com.example.flashwiz_fe.domain.model.FolderDetail
import kotlinx.coroutines.launch


class FolderViewModel() : ViewModel() {

    private val folderService = RetrofitInstance.folderApiService
    private val _folders = mutableStateOf<List<FolderDetail>>(emptyList())
    val folders: State<List<FolderDetail>> = _folders// Các thành phần khác của ViewModel

    private val _toastMessage = mutableStateOf<String?>(null)
    val toastMessage: State<String?> = _toastMessage

    // Hàm thêm thư mục mới
    fun addFolder(name: String, description: String, userId: Int, onResult: (Boolean) -> Unit) {
        val folder = Folder(name = name, descriptions = description, userId = userId)
        viewModelScope.launch {
            try {
                val response = folderService.saveFolder(folder, userId)
                if (response.isSuccessful) {
                    _toastMessage.value = "Thêm Folder thành công!"
                    onResult(true)
                } else {
                    _toastMessage.value = "Thêm Folder thất bại!"
                    onResult(false)
                }
            } catch (e: Exception) {
                _toastMessage.value = "An error occurred while adding folder!"
                onResult(false)
            }
        }
    }
    fun deleteFolder(folderId: Int) {
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

