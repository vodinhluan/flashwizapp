package com.example.flashwiz_fe.presentation.components.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.flashwiz_fe.R
import com.example.flashwiz_fe.ui.theme.LightPrimaryColor
import com.example.flashwiz_fe.ui.theme.Poppins
import com.example.flashwiz_fe.ui.theme.Shapes

@Composable
fun GeneralOptionsUI() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        GeneralSettingItem(
            icon = R.drawable.terms_of_use_ic,
            mainText = "Terms of use",
            subText = "Here are some User Terms",
            onClick = { showDialog = true }
        )

        // Hiển thị Dialog khi showDialog là true
        if (showDialog) {
            MoreCustomizationDialog(
                onDismiss = { showDialog = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GeneralSettingItem(icon: Int, mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(LightPrimaryColor)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontFamily = Poppins,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = subText,
                        fontFamily = Poppins,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun MoreCustomizationDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Terms of Use",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Chúng tôi có thể sử dụng các thông tin cá nhân của bạn cho mục đích:\n" +
                            "Nhận dạng, xác minh, nhận biết khách hàng nào đang truy cập, sử dụng dịch vụ của chúng tôi.\n" +
                            "Liên hệ với bạn qua email nhằm mục đích quản trị hoặc nâng cao quan hệ của bạn với chúng tôi hoặc việc bạn sử dụng Các Dịch Vụ của chúng tôi.\n" +
                            "Cho phép các người dùng khác tương tác hoặc thấy một số hoạt động của bạn trên Nền tảng, bao gồm thông báo cho bạn khi một người dùng khác mời bạn tham gia một sự kiện trên Nền tảng.\n" +
                            "Nghiên cứu, phân tích và phát triển (bao gồm nhưng không giới hạn phân tích dữ liệu, khảo sát, phát triển và/hoặc lập đặc tính sản phẩm và dịch vụ), để phân tích cách thức bạn sử dụng Các Dịch Vụ của chúng tôi, để cải thiện Các Dịch Vụ hoặc sản phẩm của chúng tôi và/hoặc để cải thiện trải nghiệm khách hàng",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                ) {
                    Text(text = "Close")
                }
            }
        }
    }
}
