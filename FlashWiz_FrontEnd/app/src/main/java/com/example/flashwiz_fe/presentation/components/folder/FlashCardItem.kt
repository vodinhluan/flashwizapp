import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.data.model.Flashcard
import com.example.flashwiz_fe.data.model.FlashcardDetail

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlashCardItem(
    flashcard: FlashcardDetail,
    onItemClick: (Int) -> Unit // Thêm onItemClick trở lại
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = { onItemClick(flashcard.id) }), // Thêm phản hồi khi click
        border = BorderStroke(1.dp, Color.LightGray),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Flashcard Name: ${flashcard.name}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Description: ${flashcard.descriptions}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}
