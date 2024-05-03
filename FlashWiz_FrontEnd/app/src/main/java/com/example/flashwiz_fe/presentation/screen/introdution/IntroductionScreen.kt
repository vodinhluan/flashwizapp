package com.example.flashwiz_fe.presentation.screen.introduction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.white

@Composable
fun IntroductionScreen() {
    // Header
    Column(
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
            .padding(bottom = 20.dp) // Chỉnh sửa padding bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Introduction",
                style = MaterialTheme.typography.h4,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(16.dp), // Chỉnh sửa padding
                color = white,
                fontWeight = FontWeight.SemiBold
            )

            // Thêm các thành phần khác tùy ý ở đây
        }
    }

    // Văn giới thiệu
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            Text(
                text = "Xin chào bạn đến với ứng dụng FlashWiz!",
                fontSize = 24.sp,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(top = 110.dp)
            )
        }
        item {
            Text(
                text = "Ứng dụng này giúp bạn nâng cao kiến thức thông qua việc học từ vựng, ngữ pháp và các loại câu hỏi kiểm tra. Hãy khám phá các tính năng và bắt đầu hành trình học tập của bạn ngay hôm nay. Bạn sẽ được trải nghiệm một cách học tập hiệu quả và thú vị nhờ vào các tính năng như:",
                style = MaterialTheme.typography.body2,
                fontSize = 19.sp,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
                    .padding(top = 8.dp)
            )
        }
        item {
            Text(
                text = "- Tạo và quản lý các bộ từ vựng theo chủ đề hoặc cấp độ khó",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        item {
            Text(
                text = "- Thực hiện các bài kiểm tra linh hoạt để kiểm tra kiến thức của bạn",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        item {
            Text(
                text = "- Theo dõi tiến độ học tập của bạn và nhận thông báo nhắc nhở",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        item {
            Text(
                text = "- Tích hợp tính năng ghi chú và phản hồi",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
