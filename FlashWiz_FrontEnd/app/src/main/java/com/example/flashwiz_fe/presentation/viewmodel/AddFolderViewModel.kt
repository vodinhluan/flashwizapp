package com.example.flashwiz_fe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.model.Folder
import com.example.flashwiz_fe.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class AddFolderViewModel : ViewModel() {

    private val folderService = RetrofitInstance.apiService

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
}
