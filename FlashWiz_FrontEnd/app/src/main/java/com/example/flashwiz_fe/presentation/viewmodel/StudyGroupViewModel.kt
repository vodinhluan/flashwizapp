import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.domain.model.Group
import kotlinx.coroutines.launch

class StudyGroupViewModel : ViewModel() {
    private val groupService = RetrofitInstance.groupApiService

    // Thêm trường dữ liệu userId
    var userId: Int = 0

    fun addGroup(name: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val group = Group(name = name, groupCode = "", flashcards = listOf(), userId = userId)
                val response = groupService.createGroup(group)
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
