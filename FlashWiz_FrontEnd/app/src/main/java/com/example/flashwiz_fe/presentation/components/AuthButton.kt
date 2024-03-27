package com.example.flashwiz_fe.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.ui.theme.blue
import com.example.flashwiz_fe.ui.theme.brightBlue
import com.example.flashwiz_fe.ui.theme.orange
import com.example.flashwiz_fe.ui.theme.white
import androidx.compose.material.MaterialTheme

@Composable
fun AuthButton(
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    enabled:Boolean = true,
    onButtonClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,

) {
    Button(
        modifier = modifier,
        onClick = {
            onButtonClick ()
        },

        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = backgroundColor,
            disabledContentColor = contentColor
        ),
        enabled = enabled
    ){
        if(isLoading){
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier.size(20.dp)
            )
        }else{
            Text(
                text = text,
                style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthButtonPreview() {
    AuthButton(

        text = "Login",
        backgroundColor = blue,
        contentColor = white,
        onButtonClick = { /*TODO*/ },
        isLoading = false,
        modifier = Modifier
            .fillMaxWidth()
    )
}
