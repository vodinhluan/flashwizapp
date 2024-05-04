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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.times
import com.example.flashwiz_fe.presentation.components.static.BarChart
import com.example.flashwiz_fe.presentation.components.static.BarChartData
import com.example.flashwiz_fe.presentation.components.static.StatisticDetails
import com.example.flashwiz_fe.presentation.components.static.StatisticSummaryItem
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
            text = "DATA ANALYST",
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

//Column(modifier = Modifier.background(androidx.compose.material.MaterialTheme.colors.background)) {


@Composable
fun StatisticScreen(cardApiService: CardApiService, flashcardId: Int,
                    navController: NavController) {
    val statisticViewModel: StatisticViewModel = remember {
        StatisticViewModel(cardApiService, flashcardId)
    }
    val statisticData by statisticViewModel.statisticData.observeAsState(mapOf())


    Column(modifier = Modifier.background(androidx.compose.material.MaterialTheme.colors.background)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item { StaticText(navController = navController) }

            item { StatisticDetails("Tổng số từ học được", statisticData?.get("totalCard") ?: 0) }
            item { StatisticDetails("Fail Rating", statisticData?.get("failCard") ?: 0) }
            item { StatisticDetails("Hard Rating", statisticData?.get("hardCard") ?: 0) }
            item { StatisticDetails("Good Rating", statisticData?.get("goodCard") ?: 0) }
            item { StatisticDetails("Easy Rating", statisticData?.get("easyCard") ?: 0) }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "BIỂU ĐỒ", style = MaterialTheme.typography.titleLarge)
                BarChart(
                    data = listOf(
                        BarChartData("Fail", statisticData?.get("failCard") ?: 0),
                        BarChartData("Hard", statisticData?.get("hardCard") ?: 0),
                        BarChartData("Good", statisticData?.get("goodCard") ?: 0),
                        BarChartData("Easy", statisticData?.get("easyCard") ?: 0),
                    ),
                    maxValue = statisticData?.values?.maxOrNull() ?: 1, barColor = Color.Black
                )
            }
        }
    }
}