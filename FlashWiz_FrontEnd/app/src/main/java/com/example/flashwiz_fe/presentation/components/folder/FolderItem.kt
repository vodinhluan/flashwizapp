
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.domain.model.FolderDetail

@Composable
fun FolderItem(
    folder: FolderDetail,
    onItemClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(20.dp)
                )
                .shadow(10.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp)), // Áp dụng clip cho nội dung bên trong Card
            backgroundColor = Color.White,
            elevation = 4.dp

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    Text(
                        text = folder.name,
                        style = MaterialTheme.typography.subtitle1,
                    )
                    Text(
                        text = folder.descriptions,
                        style = MaterialTheme.typography.body2,
                        maxLines = 2,  // Giới hạn số dòng
                        overflow = TextOverflow.Ellipsis  // Hiển thị dấu ba chấm khi vượt quá số dòng
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    IconButton(
                        onClick = { onDeleteClick(folder.id) }
                    ) {
                        Icon(Icons.Outlined.DeleteOutline, contentDescription = "Delete")
                    }
                    IconButton(
                        onClick =  { onItemClick(folder.id) }
                    ) {
                        Icon(Icons.Outlined.PlayArrow, contentDescription = "PlayArrow")
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FolderItemPreview() {
    val fakeFolder = FolderDetail(
        id = 1,
        name = "Sample Folder",
        descriptions = "This is a sample older description."
    )
    FolderItem(
        folder = fakeFolder,
        onItemClick = {},
        onDeleteClick = {}
    )
}
