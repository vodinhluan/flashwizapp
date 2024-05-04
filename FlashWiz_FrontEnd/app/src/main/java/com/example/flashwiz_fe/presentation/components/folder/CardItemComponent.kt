package com.example.flashwiz_fe.presentation.components.folder
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.domain.model.CardDetail
import com.example.flashwiz_fe.ui.theme.brightBlue

@Composable
fun CardItemComponent(
    card: CardDetail,
    onCardClicked: () -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onCardClicked)
            .shadow(8.dp)
            .size(200.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(brightBlue)
                    .border(BorderStroke(0.5.dp, Color.Black))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onDeleteClick(card.id) }
                    ) {
                        Icon(Icons.AutoMirrored.TwoTone.Backspace, contentDescription = "Delete", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = card.front,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.body2,
                maxLines = 2,  // Giới hạn số dòng
                overflow = TextOverflow.Ellipsis,  // Hiển thị dấu ba chấm khi vượt quá số dòng
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    // Tạo một ví dụ CardDetail để truyền vào CardItemComponent
    val sampleCard = CardDetail(
        id = 1,
        front = "Sample Front Content",
        back = "Sample Back Content",
        rating = "Good"
    )

    // Gọi CardItemComponent với ví dụ CardDetail và các hàm xử lý tương ứng
    CardItemComponent(
        card = sampleCard,
        onCardClicked = { /* Xử lý khi thẻ được nhấp */ },
        onDeleteClick = { /* Xử lý khi nút xóa được nhấp */ }
    )
}