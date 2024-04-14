
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashwiz_fe.presentation.components.BackIconComponent
import com.example.flashwiz_fe.presentation.viewmodel.FolderViewModel
@Composable
fun AddFolderScreen(
    onNavigateBack: () -> Unit
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
            BackIconComponent(onNavigateBack)
            Button(
                onClick = {
                    viewModel.addFolder(folderName, folderDescription) { isSuccess ->
                        if (isSuccess) {
                            onNavigateBack()
                        } else {

                        }
                    }
                }
            ) {
                Text("Add Folder")
            }
        }
    }
}