package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.model.Folder
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.RetrofitInstance.folderApiService
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.FolderDetail
import kotlinx.coroutines.launch


class AddFolderViewModel : ViewModel() {

    private val folderService = RetrofitInstance.folderApiService
    private val _folders = mutableStateOf<List<FolderDetail>>(emptyList())
    val folders: State<List<FolderDetail>> = _folders

    fun addFolder(name: String, description: String, onResult: (Boolean) -> Unit) {
        val folder = Folder(name = name, descriptions = description, userId = 2) // Thay đổi userId thành giá trị thích hợp
        viewModelScope.launch {
            try {
                val response = folderService.saveFolder(folder, 2)
                if (response.equals("ok")) {
                    onResult(true) // Phản hồi thành công
                } else {
                    onResult(false) // Phản hồi thất bại
                }
            } catch (e: Exception) {
                onResult(false) // Xử lý exception
            }
        }
    }
    fun deleteFolder(folderId: Int) {
        viewModelScope.launch {
            try {
                folderService.deleteFolder(folderId)
                // Gọi API để lấy danh sách mới sau khi xóa thành công
            } catch (_: Exception) {
                // Xử lý lỗi nếu cần
            }
        }
    }

    private fun fetchFolders() {
        viewModelScope.launch {
            try {
                val folders = folderService.getAllFolders()
                _folders.value = folders
            } catch (_: Exception) {
            }
        }
    }
}

