package com.example.flashwiz_fe.presentation.screen.statistic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.times
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
            modifier = Modifier.fillMaxSize().padding(16.dp),
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


@Composable
fun StatisticSummaryItem(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .fillMaxHeight(),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun StatisticDetails(title: String, data: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$title:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$data",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class BarChartData(val label: String, val value: Int)
@Composable
fun BarChart(data: List<BarChartData>, maxValue: Int, barColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { item ->
            val barHeight = (item.value.toFloat() / maxValue.toFloat()) * 100f.dp
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${item.value}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .width(35.dp)
                        .height(barHeight)
                        .background(barColor)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.label,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}