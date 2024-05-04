package com.example.flashwiz_fe.presentation.components.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flashwiz_fe.presentation.state.BottomNavigationItem

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
@Preview(showBackground = true)
@Composable
fun BottomNavigationIconPreview() {
    val homeItem = BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
        hasNews = false
    )

    val searchItem = BottomNavigationItem(
        title = "Search",
        selectedIcon = Icons.Default.Search,
        unselectedIcon = Icons.Default.Search,
        hasNews = true
    )

    val profileItem = BottomNavigationItem(
        title = "Profile",
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Default.Person,
        hasNews = false
    )

    var selectedItemIndex = 0

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationIcon(
            item = homeItem,
            index = 0,
            selectedItemIndex = selectedItemIndex
        )
        BottomNavigationIcon(
            item = searchItem,
            index = 1,
            selectedItemIndex = selectedItemIndex
        )
        BottomNavigationIcon(
            item = profileItem,
            index = 2,
            selectedItemIndex = selectedItemIndex
        )
    }
}





