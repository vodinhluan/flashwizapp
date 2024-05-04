import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.model.GroupDTO
import kotlinx.coroutines.launch

class StudyGroupViewModel : ViewModel() {
    private val groupService = RetrofitInstance.groupApiService
    private val folderService = RetrofitInstance.folderApiService

    private val _folderList = mutableStateOf<List<FolderDetail>>(emptyList())
    var selectedFolder: FolderDetail? by mutableStateOf(null)

    init {
        selectedFolder = null
    }
    fun onSelectedFolderChanged() {
        Log.d("GroupViewModel", "Selected Folder: $selectedFolder")
    }
    fun joinGroupAndUpdateList(userId: Int, groupCode: String, onSuccess: () -> Unit, onFailure: () -> Unit, updateGroups: (List<GroupDTO>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = groupService.joinGroup(userId, groupCode)
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

    fun shareFolder(userId: Int, groupId: Int, folderId: Int) {
        viewModelScope.launch {
            try {
                val response = groupService.shareFolder(userId, groupId, folderId)
                if(response.isSuccessful) {
                    val folderId = response.body()
                    val updateFolders = groupService.getListFolderByGroupId(groupId)
//                    updateFolders(updateFolders)
                    Log.d("GroupViewModel", "Shared folder ID: $folderId")
                } else {
                    Log.e("GroupViewModel", "Failed to share folder, response code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GroupViewModel", "Failed to share folder, exception: ${e.message}")
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