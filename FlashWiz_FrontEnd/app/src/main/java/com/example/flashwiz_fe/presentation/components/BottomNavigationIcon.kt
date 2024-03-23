package com.example.flashwiz_fe.presentation.components


import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.flashwiz_fe.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationIcon(
    item: BottomNavigationItem,
    index: Int,
    selectedItemIndex: Int
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge { Text(text = item.badgeCount.toString()) }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        Icon(
            imageVector = if (index == selectedItemIndex) {
                item.selectedIcon
            } else item.unselectedIcon,
            contentDescription = item.title
        )
    }
}
