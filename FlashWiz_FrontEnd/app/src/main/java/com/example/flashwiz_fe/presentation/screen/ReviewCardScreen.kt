import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReviewCardScreen() {
    var isFront by remember { mutableStateOf(true) } // Track if the front of the card is shown
    var showEvaluationBar by remember { mutableStateOf(false) } // Track if the evaluation bar is shown

    val frontText = "Front of the Card"
    val backText = "Back of the Card"
    val flashcardName = "Flashcard Title"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = flashcardName,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Card Display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.LightGray)
                .padding(16.dp)
        ) {
            // Show either front text or back text based on the value of isFront
            if (isFront) {
                Text(
                    text = frontText,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = backText,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Button to flip the card
        if (!showEvaluationBar) {
            Button(
                onClick = {
                    isFront = !isFront
                    showEvaluationBar = !showEvaluationBar
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Flip Card")
            }
        }

        // Evaluation Bar
        AnimatedVisibility(
            visible = showEvaluationBar,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EvaluationButton(text = "Fail", color = Color.Red)
                EvaluationButton(text = "Hard", color = Color.Yellow)
                EvaluationButton(text = "Good", color = Color.Cyan)
                EvaluationButton(text = "Easy", color = Color.Green)
            }
        }
    }
}

@Composable
fun EvaluationButton(text: String, color: Color) {
    Button(onClick = { /* Handle evaluation */ },
        colors = ButtonDefaults.buttonColors(backgroundColor = color)) {
        Text(text = text)
    }
}

