package com.example.flashwiz_fe.presentation.components.home


import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.flashwiz_fe.presentation.components.BottomNavigationIcon
import com.example.flashwiz_fe.presentation.state.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { onItemSelected(index) },
                label = { Text(text = item.title) },
                alwaysShowLabel = false,
                icon = {
                    BottomNavigationIcon(
                        item = item,
                        index = index,
                        selectedItemIndex = selectedItemIndex
                    )
                }
            )
        }
    }
}

