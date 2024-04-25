import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.SecondaryColor
@Composable
fun DeleteDialog(
    IdtoDelete: Int,
    itemType: String,
    onDismiss: () -> Unit,
    onChangeSuccess: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Delete this $itemType",
                fontFamily = Poppins,
                color = SecondaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        text = {
            Text(
                text = "Are you sure you want to delete this $itemType?",
                fontFamily = Poppins,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                TextButton(
                    onClick = { onDismiss() },
                    modifier = Modifier.padding(8.dp).weight(1f) // Sử dụng weight để cân đối kích thước
                ) {
                    Text(
                        "No",
                        color = SecondaryColor,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                }
                Button(
                    onClick = {
                        onChangeSuccess(IdtoDelete)
                        onDismiss()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text("Yes")
                }
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )

}
