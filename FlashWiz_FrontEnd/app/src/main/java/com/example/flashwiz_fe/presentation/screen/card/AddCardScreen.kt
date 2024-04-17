import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.flashwiz_fe.presentation.state.CardState
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import com.example.flashwiz_fe.presentation.components.CustomButtonComponent
import com.example.flashwiz_fe.presentation.screen.card.RichTextEditor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AddCardScreen(cardViewModel: CardViewModel, navController: NavHostController) {
    val cardState = remember { mutableStateOf(CardState()) }
    val saveSuccess by cardViewModel.saveSuccess.collectAsState()
    val context = LocalContext.current

    // Handle back button press
    BackHandler {
        // Handle back press logic here
        // For example, navigate back or show a confirmation dialog
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "CARD INFO",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Rich Text Editor
            RichTextEditor(
                modifier = Modifier.fillMaxSize(),
                initialText = cardState.value.frontText,
                onTextChanged = { newText ->
                    cardState.value = cardState.value.copy(frontText = newText)
                }
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // SAVE BUTTON
                CustomButtonComponent(
                    text = "Save This Card",
                    onClick = {
                        val card = com.example.flashwiz_fe.domain.model.Card(
                            front = cardState.value.frontText.trim(),
                            back = cardState.value.backText.trim()
                        )
                        cardViewModel.saveCard(card)
                    },
                    modifier = Modifier.wrapContentSize(),
                    backgroundColor = Color.LightGray,
                    contentColor = Color.Blue,
                    borderColor = Color.Black
                )

            }
            LaunchedEffect(saveSuccess) {
                if (saveSuccess) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
