package com.example.flashwiz_fe.presentation.screen.card

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

@Composable
fun RichTextEditor(
    modifier: Modifier = Modifier,
    initialText: String = "",
    onTextChanged: (String) -> Unit
) {
    val context = LocalContext.current

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            TextInputEditText(ctx).apply {
                // Set initial text
                setText(initialText)

                // Set up text change listener
                addTextChangedListener { editable ->
                    onTextChanged(editable.toString())
                }
            }
        }
    )
}
    