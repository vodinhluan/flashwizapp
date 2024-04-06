package com.example.flashwiz_fe.presentation.components.home


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashwiz_fe.ui.theme.orange

@Composable
fun SearchBar(
    description:String,
    hint:String,
//    leadingIcon: ImageVector,
    textValue:String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textColor: Color,
    cursorColor: Color,
    onValueChanged:(String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick:(()->Unit)?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ){

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .border(0.5.dp, textColor, RoundedCornerShape(25.dp))
                .height(50.dp)
                .shadow(3.dp, RoundedCornerShape(25.dp)),
            value = textValue,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = cursorColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(25.dp),
            singleLine = true,

            placeholder = {
                Text(
                    hint,
                    style = MaterialTheme.typography.body2
                )
            },
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    SearchBar(
        description = "Search",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,0.dp,10.dp,5.dp),
        hint = "Search",
        textValue = "Search...",
        textColor = Color.Black,
        cursorColor = orange,
        onValueChanged = {},
        trailingIcon = Icons.Filled.RemoveRedEye,
        onTrailingIconClick = {}
    )
}



