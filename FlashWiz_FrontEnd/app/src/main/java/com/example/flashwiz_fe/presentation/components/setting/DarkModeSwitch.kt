package com.example.flashwiz_fe.presentation.components.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.ui.theme.Poppins
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashwiz_fe.presentation.viewmodel.ThemeViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun DarkModeSwitch(viewModel: ThemeViewModel = viewModel()) {
    val isDarkMode by viewModel.darkThemeEnabled.observeAsState(false)

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.toggleTheme() }
                .padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode",
                fontFamily = Poppins,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.Nightlight,
                contentDescription = if (isDarkMode) "Switch to light mode" else "Switch to dark mode",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}