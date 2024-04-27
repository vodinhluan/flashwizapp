package com.example.flashwiz_fe.presentation.components.home


import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashwiz_fe.presentation.state.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
        NavigationBar(
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItemIndex == index
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemSelected(index) },
                    label = {
                        Text(
                            text = item.title,
                            color = if (isSelected) Color.Black else Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    },
                    alwaysShowLabel = false,
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            tint = if (isSelected) Color.Black else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        }

}




@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Default.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Default.Search,
            unselectedIcon = Icons.Default.Search,
            hasNews = true
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Default.Person,
            unselectedIcon = Icons.Default.Person,
            hasNews = false
        )
    )

    var selectedItemIndex = 0

    BottomNavigationBar(
        items = items,
        selectedItemIndex = selectedItemIndex,
        onItemSelected = { index ->
            selectedItemIndex = index
        }
    )
}




