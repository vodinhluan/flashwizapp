package com.example.flashwiz_fe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.Flashcard
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.model.Group
import com.example.flashwiz_fe.domain.model.GroupDTO
import kotlinx.coroutines.launch

class StudyGroupViewModel : ViewModel() {
    private val groupService = RetrofitInstance.groupApiService
    fun joinGroupAndUpdateList(userId: Int, groupCode: String, onSuccess: () -> Unit, onFailure: () -> Unit, updateGroups: (List<GroupDTO>) -> Unit) {
    viewModelScope.launch {
        try {
            val response = groupService.joinGroup(userId, groupCode)
            if (response.isSuccessful) {
                // Nếu tham gia nhóm thành công, cập nhật lại danh sách nhóm
                val updatedGroups = groupService.getUserGroups(userId)
                // Gọi hàm onSuccess để thông báo rằng tham gia nhóm thành công
                updateGroups(updatedGroups)
                onSuccess()
            } else {
                onFailure()
            }
        } catch (e: Exception) {
            onFailure()
        }
    }


}
    fun createGroup(userId: Int, groupName: String, onSuccess: () -> Unit, onFailure: () -> Unit,updateGroups: (List<GroupDTO>) -> Unit) {
        val groupDTO = GroupDTO(id=userId, groupName=groupName, groupCode = "" )
        viewModelScope.launch {
            try {
                val response = groupService.createGroup(userId, groupDTO)
                if (response.isSuccessful) {
                    val updatedGroups = groupService.getUserGroups(userId)
                    updateGroups(updatedGroups)
                    onSuccess()
                } else {
                    onFailure()
                }
            } catch (e: Exception) {
                onFailure()
            }
        }
    }

}



