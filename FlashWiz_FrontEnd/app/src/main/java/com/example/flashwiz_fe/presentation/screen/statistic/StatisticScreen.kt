package com.example.flashwiz_fe.presentation.screen.statistic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.flashwiz_fe.presentation.components.statistic.BarChart
//import com.example.flashwiz_fe.presentation.components.static.BarChartData
//import com.example.flashwiz_fe.presentation.components.static.StatisticDetails
//import com.example.flashwiz_fe.presentation.components.static.StatisticSummaryItem
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.SecondaryColor





@Composable
fun StaticText() {
    androidx.compose.material.Text(
        text = "Static",
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


@Composable
fun StatisticScreen() {
    Column(modifier = Modifier.background(androidx.compose.material.MaterialTheme.colors.background)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { StaticText() }


            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatisticSummaryItem("Reviews Today", "2/10", Modifier.weight(1f))
                    StatisticSummaryItem("Streak (Days)", "32", Modifier.weight(1f))
                }
            }

            item { StatisticDetails("Cards Studied", 150) }
            item { StatisticDetails("Hours Studied", 12) }
            item { StatisticDetails("Average Score", 85) }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Daily Statistics", style = MaterialTheme.typography.titleLarge)
                BarChart(
                    data = listOf(
                        BarChartData("Mon", 10),
                        BarChartData("Tue", 20),
                        BarChartData("Wed", 30),
                        BarChartData("Thu", 40),
                        BarChartData("Fri", 50),
                        BarChartData("Sat", 35),
                        BarChartData("Sun", 25)
                    ),
                    maxValue = 50, barColor = Color.Black
                )
            }
        }
    }
}