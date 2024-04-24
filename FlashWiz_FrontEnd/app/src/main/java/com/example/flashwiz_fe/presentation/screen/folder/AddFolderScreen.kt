import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.flashwiz_fe.presentation.components.BackIconComponent
import com.example.flashwiz_fe.presentation.viewmodel.FolderViewModel
import com.example.flashwiz_fe.util.ScreenRoutes

@Composable
fun AddFolderScreen(
    onNavigateBack: () -> Unit,
    initialUserId: Int?,
    navController: NavHostController
) {
    val viewModel: FolderViewModel = viewModel()
    var folderName by remember { mutableStateOf("") }
    var folderDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add Folder",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = folderName,
            onValueChange = { folderName = it },
            label = { Text("Folder Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = folderDescription,
            onValueChange = { folderDescription = it },
            label = { Text("Folder Description") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        onNavigateBack()                }
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    Log.d("AddFlashcardScreen", "Folder ID: $initialUserId")
                    if (initialUserId != null) {
                        viewModel.addFolder(
                            folderName,
                            folderDescription,
                            initialUserId
                        ) {
                            Log.d("AddFolderScreen", "onNavigateBack is called")
                            onNavigateBack() // Gọi hàm onNavigateBack
                        }
                    }
                }
            ) {
                Text("Add Folder")
            }
        }
    }
}
