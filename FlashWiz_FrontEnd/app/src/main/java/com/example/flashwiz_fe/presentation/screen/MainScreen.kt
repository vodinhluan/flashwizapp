package com.example.flashwiz_fe.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DensityMedium
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DensityMedium
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.presentation.components.home.BottomNavigationBar


import com.example.flashwiz_fe.presentation.screen.setting.AccountScreen

import com.example.flashwiz_fe.presentation.screen.folder.HomeScreen
import com.example.flashwiz_fe.presentation.screen.group.StudyGroupScreen
import com.example.flashwiz_fe.presentation.screen.introduction.IntroductionScreen


import com.example.flashwiz_fe.presentation.screen.setting.AccountScreen
import com.example.flashwiz_fe.presentation.screen.statistic.StatisticScreen
import com.example.flashwiz_fe.presentation.state.BottomNavigationItem
import com.example.flashwiz_fe.presentation.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable



fun MainScreen(navController: NavHostController, userId: Int?) {

    val viewModel: ThemeViewModel = viewModel()


    val items = listOf(

        // Phần giới thiệu
        BottomNavigationItem(
            title = "Intro",
            selectedIcon = Icons.Filled.DensityMedium,
            unselectedIcon = Icons.Outlined.DensityMedium,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Group",
            selectedIcon = Icons.Filled.Group,
            unselectedIcon = Icons.Outlined.Group,
            hasNews = false,
        ),

        BottomNavigationItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false,
        ),

    )

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(

                    items = items,
                    selectedItemIndex = selectedItemIndex,
                    onItemSelected = { index -> selectedItemIndex = index }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                when (selectedItemIndex) {

                    0 -> IntroductionScreen ()
                    1 -> if (userId != null) {
                        viewModel.darkThemeEnabled.value?.let {
                            HomeScreen(
                                navController = navController,
                                apiService = RetrofitInstance.folderApiService,
                                userId = userId,
                                isDarkMode = it
                            )
                        }
                    }

                    2 -> StudyGroupScreen(navController = navController,
                        groupApiService = RetrofitInstance.groupApiService,
                        folderApiService = RetrofitInstance.folderApiService,
                        userId = userId)
                    3 -> AccountScreen(navController = navController,  userId = userId

                    )

                    else -> if (userId != null) {
                        viewModel.darkThemeEnabled.value?.let {
                            HomeScreen(
                                navController = navController,
                                apiService = RetrofitInstance.folderApiService,
                                userId = userId,
                                isDarkMode = it
                            )
                        }

                    }
                }
            }
        }
    }
}