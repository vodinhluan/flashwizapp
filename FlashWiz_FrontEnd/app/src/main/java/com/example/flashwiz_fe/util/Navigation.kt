package com.example.flashwiz_fe.util

import AddFolderScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashwiz_fe.data.CardRepositoryImpl
import com.example.flashwiz_fe.data.remote.RetrofitInstance
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.presentation.screen.CardScreen
import com.example.flashwiz_fe.presentation.screen.HomeScreen
import com.example.flashwiz_fe.presentation.screen.LoginScreen
import com.example.flashwiz_fe.presentation.screen.MainScreen
import com.example.flashwiz_fe.presentation.screen.RegisterScreen
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.LoginScreen.route
    ) {
        composable(ScreenRoutes.LoginScreen.route) {
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.MainScreen.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(ScreenRoutes.RegisterScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.RegisterScreen.route) {
            RegisterScreen(
                onRegisterSuccessNavigation = {
                    navController.navigate(ScreenRoutes.MainScreen.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(ScreenRoutes.LoginScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.MainScreen.route) {
            MainScreen(navController)
        }
        composable(ScreenRoutes.AddFolderScreen.route) {
            AddFolderScreen(
//                onAddFolder = { folderName, folderDescription ->
//                    // Xử lý khi người dùng thêm thư mục ở đây
//                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(ScreenRoutes.AddCardScreen.route) {
            val cardViewModel: CardViewModel = remember {
                val cardRepository: CardRepository = CardRepositoryImpl(RetrofitInstance.apiService)

                CardViewModel(cardRepository)
            } ?: error("Cannot create CardViewModel")

            CardScreen(cardViewModel = cardViewModel, navController = navController)
        }
    }
}

sealed class ScreenRoutes(val route: String) {
    object LoginScreen : ScreenRoutes("login_screen")
    object RegisterScreen : ScreenRoutes("register_screen")
    object MainScreen : ScreenRoutes("main_screen")
    object HomeScreen : ScreenRoutes("home_screen")
    object AddFolderScreen : ScreenRoutes("add_folder_screen")
    object AddCardScreen : ScreenRoutes("add_card_screen")
//    object AddFlashcardScreen : ScreenRoutes("add_flashcard_screen")
}
