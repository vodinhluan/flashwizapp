package com.example.flashwiz_fe.presentation.screen.group


//import com.example.flashwiz_fe.presentation.components.home.AddItemNewGroup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashwiz_fe.presentation.components.TextAreaComponent
import com.example.flashwiz_fe.presentation.components.group.AddItemNewGroup
import com.example.flashwiz_fe.presentation.components.home.SearchBar

@Composable
fun StudyGroupScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var text by remember { mutableStateOf(TextFieldValue()) }
    Surface(
        modifier = Modifier.fillMaxSize(),
                color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.Cyan),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "STUDY GROUP",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(16.dp)
                )
//                AddItemNewGroup(navController = navController) #Phu Le Comment
                AddItemNewGroup(navController = navController, itemType = "Group", groupId = null)


            }

            SearchBar(
                description = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 5.dp),
                hint = "Search",
                textValue = searchQuery,
                textColor = Color.Black,
                cursorColor = Color.LightGray,
                onValueChanged = { newValue ->
                    searchQuery = newValue
                }, trailingIcon = Icons.Filled.RemoveRedEye,
                onTrailingIconClick = {}
            )
            TextAreaComponent()
            }
        }
    }

@Composable
fun StudyGroupScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "Trang Study Group", modifier = Modifier.fillMaxSize())
    }
}

