package com.example.flashwiz_fe.presentation.screen.statistic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.presentation.viewmodel.StatisticViewModel
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.SecondaryColor

@Composable
fun StaticText(navController: NavController) {

    Row {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .clickable {
                    navController.navigateUp()
                }
//            .padding(16.dp)
        )
        androidx.compose.material.Text(
            text = "DATA ANALYSIS",
            fontFamily = Poppins,
            color = SecondaryColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }

}

@Composable
fun StatisticScreen(cardApiService: CardApiService, flashcardId: Int, navController: NavController) {
    val statisticViewModel = remember { StatisticViewModel(cardApiService, flashcardId) }
    val totalCard = statisticViewModel.totalCard
    val failCard = statisticViewModel.failCard
    val hardCard = statisticViewModel.hardCard
    val goodCard = statisticViewModel.goodCard
    val easyCard = statisticViewModel.easyCard

    Column(modifier = Modifier.background(androidx.compose.material.MaterialTheme.colors.background)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { StaticText(navController = navController) }

            item { StatisticDetails("Total", totalCard) }
            item { StatisticDetails("Fail Rating", failCard) }
            item { StatisticDetails("Hard Rating", hardCard) }
            item { StatisticDetails("Good Rating", goodCard) }
            item { StatisticDetails("Easy Rating", easyCard) }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "CHART",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = Poppins,
                    color = SecondaryColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 10.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
                BarChart(
                    data = listOf(
                        BarChartData("Fail", failCard),
                        BarChartData("Hard", hardCard),
                        BarChartData("Good", goodCard),
                        BarChartData("Easy", easyCard),
                    ),
                    maxValue = listOf(failCard, hardCard, goodCard, easyCard).maxOrNull() ?: 1,
                    barColor = Color.Black
                )
            }
        }
    }
}