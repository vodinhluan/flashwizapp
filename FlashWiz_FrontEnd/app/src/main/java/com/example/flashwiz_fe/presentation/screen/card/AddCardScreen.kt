package com.example.flashwiz_fe.presentation.screen.card


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.presentation.components.CustomButtonComponent
import com.example.flashwiz_fe.presentation.state.CardState
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import com.example.flashwiz_fe.ui.theme.brightBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




@Composable
fun AddCardScreen(    onNavigateBack: () -> Unit,
//                      onNavigateUp: () ->Unit,
                      cardViewModel: CardViewModel,  navController: NavHostController,initialFlashcardId: Int?) {
    val cardState = remember { mutableStateOf(CardState()) }
    val saveSuccess by cardViewModel.saveSuccess.collectAsState()
    val context = LocalContext.current
    var isBoldFront by remember { mutableStateOf(false) }
    var isItalicFront by remember { mutableStateOf(false) }
    var isBoldBack by remember { mutableStateOf(false) }
    var isItalicBack by remember { mutableStateOf(false) }
    var textAlignFront by remember { mutableStateOf(TextAlign.Start) }
    var textAlignBack by remember { mutableStateOf(TextAlign.Start) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brightBlue,
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable {
                                onNavigateBack()
                            }
                            .padding(16.dp)

                    )
                    Spacer(modifier = Modifier.width(26.dp))
                    Text(
                        text = "Thông tin Card",
                        style = androidx.compose.material.MaterialTheme.typography.h4,
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        color = Color.White,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "FRONT CARD",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                )
                androidx.compose.material.Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .shadow(8.dp)
                        .size(250.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    backgroundColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButtonWithText(
                                text = "B",
                                isBold = isBoldFront,
                                onClick = { isBoldFront = !isBoldFront }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButtonWithText(
                                text = "I",
                                isItalic = isItalicFront,
                                onClick = { isItalicFront = !isItalicFront }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .size(20.dp),
                                onClick = { textAlignFront = TextAlign.Start }
                            ) {
                                Icon(Icons.Default.FormatAlignLeft, contentDescription = "Align Left")
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .size(20.dp),
                                onClick = { textAlignFront = TextAlign.Center }
                            ) {
                                Icon(Icons.Default.FormatAlignCenter, contentDescription = "Align Center")
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .size(20.dp),
                                onClick = { textAlignFront = TextAlign.End }
                            ) {
                                Icon(Icons.Default.FormatAlignRight, contentDescription = "Align Right")
                            }

                        }
                        TextField(
                            value = cardState.value.frontText,
                            onValueChange = { cardState.value = cardState.value.copy(frontText = it) },
                            label = { Text("") },
                            textStyle = TextStyle.Default.copy(
                                fontSize = 20.sp,
                                fontWeight = if (isBoldFront) FontWeight.Bold else FontWeight.Normal,
                                fontStyle = if (isItalicFront) FontStyle.Italic else FontStyle.Normal,
                                textAlign = textAlignFront
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    }
                }
                Text(
                    text = "BACK CARD",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                )
                androidx.compose.material.Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .shadow(8.dp)
                        .size(250.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    backgroundColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButtonWithText(
                                text = "B",
                                isBold = isBoldBack,
                                onClick = { isBoldBack = !isBoldBack }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButtonWithText(
                                text = "I",
                                isItalic = isItalicBack,
                                onClick = { isItalicBack = !isItalicBack  }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .size(20.dp),
                                onClick = { textAlignBack = TextAlign.Start }
                            ) {
                                Icon(Icons.Default.FormatAlignLeft, contentDescription = "Align Left")
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .size(20.dp),
                                onClick = { textAlignBack = TextAlign.Center }
                            ) {
                                Icon(Icons.Default.FormatAlignCenter, contentDescription = "Align Center")
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .size(20.dp),
                                onClick = { textAlignBack = TextAlign.End }
                            ) {
                                Icon(Icons.Default.FormatAlignRight, contentDescription = "Align Right")
                            }

                        }
                        TextField(
                            value = cardState.value.backText,
                            onValueChange = { cardState.value = cardState.value.copy(backText = it) },
                            label = { Text("") },
                            textStyle = TextStyle.Default.copy(
                                fontSize = 20.sp,
                                fontWeight = if (isBoldBack) FontWeight.Bold else FontWeight.Normal,
                                fontStyle = if (isItalicBack) FontStyle.Italic else FontStyle.Normal,
                                textAlign = textAlignBack
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)

                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // SAVE BUTTON
                CustomButtonComponent(
                    text = "Save This Card",
                    backgroundColor = brightBlue,
                    contentColor = Color.White,
                    borderColor = Color.Black,
                    onClick = {
                        onNavigateBack()
                        val card = Card(
                            front = cardState.value.frontText.trim(),
                            back = cardState.value.backText.trim()
                        )
                        Log.d("AddCardScreen", "Flashcard ID: $initialFlashcardId")
                        if (initialFlashcardId != null) {
                            cardViewModel.saveCard(
                                Card(
                                    front = cardState.value.frontText.trim(),
                                    back = cardState.value.backText.trim()
                                ),
                                initialFlashcardId
                            )
                        }
                    },
                    modifier = Modifier.wrapContentSize(),
                )

            }
            LaunchedEffect(saveSuccess) {
                if (saveSuccess) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun CardInfoPreview() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            tint = Color.White,
            contentDescription = "Back",
            modifier = Modifier
                .clickable {
                    // Xử lý sự kiện khi nhấp vào nút back
                }
                .padding(16.dp)

        )
        Spacer(modifier = Modifier.width(21.dp))
        Text(
            text = "Thông tin Card",
            style = androidx.compose.material.MaterialTheme.typography.h4,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            color = Color.White,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Preview
@Composable
fun FrontCardPreview() {
    val cardState = remember { mutableStateOf(CardState()) }
    val context = LocalContext.current
    var isBoldFront by remember { mutableStateOf(false) }
    var isItalicFront by remember { mutableStateOf(false) }
    var isBoldBack by remember { mutableStateOf(false) }
    var isItalicBack by remember { mutableStateOf(false) }
    var textAlignFront by remember { mutableStateOf(TextAlign.Start) }
    var textAlignBack by remember { mutableStateOf(TextAlign.Start) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FRONT CARD",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        )
        androidx.compose.material.Card(
            modifier = Modifier
                .padding(8.dp)
                .shadow(8.dp)
                .size(250.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButtonWithText(
                        text = "B",
                        isBold = isBoldFront,
                        onClick = { isBoldFront = !isBoldFront }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButtonWithText(
                        text = "I",
                        isItalic = isItalicFront,
                        onClick = { isItalicFront = !isItalicFront }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = { textAlignFront = TextAlign.Start }
                    ) {
                        Icon(Icons.Default.FormatAlignLeft, contentDescription = "Align Left")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = { textAlignFront = TextAlign.Center }
                    ) {
                        Icon(Icons.Default.FormatAlignCenter, contentDescription = "Align Center")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = { textAlignFront = TextAlign.End }
                    ) {
                        Icon(Icons.Default.FormatAlignRight, contentDescription = "Align Right")
                    }

                }
                TextField(
                    value = cardState.value.frontText,
                    onValueChange = { cardState.value = cardState.value.copy(frontText = it) },
                    label = { Text("") },
                    textStyle = TextStyle.Default.copy(
                        fontSize = 20.sp,
                        fontWeight = if (isBoldFront) FontWeight.Bold else FontWeight.Normal,
                        fontStyle = if (isItalicFront) FontStyle.Italic else FontStyle.Normal,
                        textAlign = textAlignFront
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)

                )
            }
        }
        Text(
            text = "BACK CARD",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        )
        androidx.compose.material.Card(
            modifier = Modifier
                .padding(8.dp)
                .shadow(8.dp)
                .size(250.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButtonWithText(
                        text = "B",
                        isBold = isBoldBack,
                        onClick = { isBoldBack = !isBoldBack }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButtonWithText(
                        text = "I",
                        isItalic = isItalicBack,
                        onClick = { isItalicBack = !isItalicBack  }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = { textAlignBack = TextAlign.Start }
                    ) {
                        Icon(Icons.Default.FormatAlignLeft, contentDescription = "Align Left")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = { textAlignBack = TextAlign.Center }
                    ) {
                        Icon(Icons.Default.FormatAlignCenter, contentDescription = "Align Center")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        modifier = Modifier
                            .size(20.dp),
                        onClick = { textAlignBack = TextAlign.End }
                    ) {
                        Icon(Icons.Default.FormatAlignRight, contentDescription = "Align Right")
                    }

                }
                TextField(
                    value = cardState.value.backText,
                    onValueChange = { cardState.value = cardState.value.copy(frontText = it) },
                    label = { Text("") },
                    textStyle = TextStyle.Default.copy(
                        fontSize = 20.sp,
                        fontWeight = if (isBoldBack) FontWeight.Bold else FontWeight.Normal,
                        fontStyle = if (isItalicBack) FontStyle.Italic else FontStyle.Normal,
                        textAlign = textAlignBack
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
        }
    }
}





