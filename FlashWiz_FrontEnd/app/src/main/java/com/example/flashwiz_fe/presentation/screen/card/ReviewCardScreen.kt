import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ReviewCardScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            FlippingCard()
        }
    }
}

@Composable
fun FlippingCard() {
    var rotated by remember { mutableStateOf(false) }
    var showEvaluationBar by remember { mutableStateOf(false) }
    val rotate by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(600)
    )

    // Listen to changes in the `rotated` state and trigger side effects
    LaunchedEffect(rotated) {
        if (rotated) {
            // Trigger the evaluation bar to show after the flip animation completes
            delay(600)  // Wait for the animation to complete
            showEvaluationBar = true
        } else {
            showEvaluationBar = false
        }
    }

    Card(
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffEFEFEF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(320.dp)
            .graphicsLayer {
                rotationY = rotate
                cameraDistance = 10 * density
            }
            .fillMaxWidth()
            .clickable { rotated = !rotated }
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (rotate < 90f) {
                Text(
                    text = "This is the front of the card",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            } else {
                Text(
                    text = "This is the back of the card",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.graphicsLayer { rotationY = 180f }
                )
            }
        }
    }

    // Show evaluation buttons after card is flipped
    AnimatedVisibility(
        visible = showEvaluationBar,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        EvaluationBar()
    }
}

@Composable
fun EvaluationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween // Sử dụng SpaceBetween để phân bổ đều
    ) {
        EvaluationButton(text = "Fail", color = Color.Red, weight = 6f)
        EvaluationButton(text = "Hard", color = Color.Yellow, weight = 6f)
        EvaluationButton(text = "Good", color = Color.Cyan, weight = 6f)
        EvaluationButton(text = "Easy", color = Color.Green, weight = 6f)
    }
}

@Composable
fun EvaluationButton(text: String, color: Color, weight: Float) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .padding(horizontal = 2.dp)
            .height(48.dp) // Cố định chiều cao nút
            .background(color, RoundedCornerShape(10.dp))
            .clickable { /* Handle button click */ }
            .padding(horizontal = 3.dp), // Thêm padding ngang sau khi áp dụng weight
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black)
    }
}


