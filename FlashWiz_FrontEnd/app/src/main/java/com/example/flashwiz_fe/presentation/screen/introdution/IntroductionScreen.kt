package com.example.flashwiz_fe.presentation.screen.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashwiz_fe.R

import com.example.flashwiz_fe.presentation.screen.setting.AppTheme
import com.example.flashwiz_fe.presentation.viewmodel.ThemeViewModel
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white

import androidx.compose.foundation.layout.*



import androidx.compose.runtime.getValue

@Composable
fun IntroductionScreen(themeViewModel: ThemeViewModel = viewModel()) {
    val isDarkModeEnabled by themeViewModel.darkThemeEnabled.observeAsState()
    val viewModel: ThemeViewModel = viewModel()
    val isDarkMode by viewModel.darkThemeEnabled.observeAsState(false)
    isDarkModeEnabled?.let {
        AppTheme(it) {
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = brightBlue,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.StarBorder,
                            tint = white,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 30.dp)
                        )

                        Text(
                            text = "Introduction",
                            style = MaterialTheme.typography.h4,
                            fontFamily = FontFamily.Cursive,
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            color = white,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        Icon(
                            imageVector = Icons.Rounded.StarBorder,
                            tint = white,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 30.dp)
                        )
                    }
                }

                // Văn giới thiệu
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    item {
                        Text(
                            text = "Chào mừng bạn đến với ứng dụng FlashWiz!",
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onBackground,
                            modifier = Modifier.padding(top = 90.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.flashwiz_high_resolution_logo),
                            contentDescription = "App Image",
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .height(200.dp),

                            contentScale = ContentScale.FillWidth
                        )
                    }
                    item {
                        Text(
                            text = "FlashWiz là trợ thủ đắc lực trong hành trình học tập của bạn.",
                            style = MaterialTheme.typography.body1,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                    item {
                        Text(
                            text = "Ứng dụng này giúp bạn nâng cao kiến thức thông " +
                                    "qua việc học từ vựng, ngữ pháp và các loại câu hỏi kiểm tra. " +
                                    "Hãy khám phá các tính năng và bắt đầu hành trình học tập của " +
                                    "bạn ngay hôm nay ",
                            style = MaterialTheme.typography.body2,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center, // Căn giữa văn bản
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(top = 16.dp)
                        )
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = "Chúc bạn sẽ có được trải nghiệm học tập thật là thú vị !",
                                style = MaterialTheme.typography.h4,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun IntroductionScreenPreview() {
    MaterialTheme {
        IntroductionScreen()
    }
}