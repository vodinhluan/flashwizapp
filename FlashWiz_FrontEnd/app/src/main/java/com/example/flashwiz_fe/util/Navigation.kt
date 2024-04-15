package com.example.flashwiz_fe.util

import AddFolderScreen
import ReviewCardScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashwiz_fe.data.CardRepositoryImpl
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.presentation.screen.flashcard.AddFlashcardScreen
import com.example.flashwiz_fe.presentation.screen.auth.LoginScreen
import com.example.flashwiz_fe.presentation.screen.MainScreen
import com.example.flashwiz_fe.presentation.screen.auth.RegisterScreen
import com.example.flashwiz_fe.presentation.screen.card.AddCardScreen
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel

@Composable
fun Navigation(darkTheme: Any, onThemeUpdated: () -> Unit) {
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
                    navController.navigate(ScreenRoutes.LoginScreen.route) {
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
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }


        composable(ScreenRoutes.AddFlashcardScreen.route) {
            AddFlashcardScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                initialFolderId = navController.currentBackStackEntry?.arguments?.getInt("folderId")
            )
        }


        composable(ScreenRoutes.AddCardScreen.route) {
            val cardViewModel: CardViewModel = remember {
                val cardRepository: CardRepository = CardRepositoryImpl(RetrofitInstance.cardApiService)

                CardViewModel(cardRepository)
            } ?: error("Cannot create CardViewModel")
           AddCardScreen(cardViewModel = cardViewModel, navController = navController)
        }

        composable(ScreenRoutes.ReviewCardScreen.route) {
            ReviewCardScreen()
        }


    }
}






