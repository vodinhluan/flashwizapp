@file:Suppress("NAME_SHADOWING")

package com.example.flashwiz_fe.util

import AddFolderScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flashwiz_fe.data.CardRepositoryImpl
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.presentation.screen.flashcard.AddFlashcardScreen
import com.example.flashwiz_fe.presentation.screen.auth.LoginScreen
import com.example.flashwiz_fe.presentation.screen.MainScreen
import com.example.flashwiz_fe.presentation.screen.auth.RegisterScreen

import com.example.flashwiz_fe.presentation.screen.ReviewCardScreen
import com.example.flashwiz_fe.presentation.screen.card.AddCardScreen
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel

@Composable
fun Navigation(darkTheme: Any, onThemeUpdated: () -> Unit) {
    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val userPreferences = UserPreferences(context)
        println("Email đang đăng nhập: ${userPreferences.getUserEmail()}")
        if (userPreferences.getIsLoggedIn()) {
            navController.navigate(ScreenRoutes.MainScreen.route) {
                popUpTo(0)
            }
        }
    }
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
        composable(ScreenRoutes.AddFlashcardScreen.route + "/{folderId}") { backStackEntry ->
            val navController = rememberNavController()
            val folderId = backStackEntry.arguments?.getString("folderId")?.toIntOrNull()

            AddFlashcardScreen(
                onNavigateBack = { navController.popBackStack() },
                initialFolderId = folderId,
                navController = navController
            )
        }



        composable(ScreenRoutes.AddCardScreen.route+"/{flashcardId}") { backStackEntry ->
            val navController = rememberNavController()
            val flashcardId = backStackEntry.arguments?.getString("flashcardId")?.toIntOrNull()

            val cardViewModel: CardViewModel = remember {
                val cardRepository: CardRepository = CardRepositoryImpl(RetrofitInstance.cardApiService)

                CardViewModel(cardRepository)
            } ?: error("Cannot create CardViewModel")
            AddCardScreen(cardViewModel = cardViewModel, navController = navController, initialFlashcardId = flashcardId)
        }
        composable(ScreenRoutes.ReviewCardScreen.route) {
            ReviewCardScreen()
        }
        composable(ScreenRoutes.NotificationScreen.route){
        }
    }
}

//        composable(
//            route = ScreenRoutes.FlashcardDetailScreen.route + "/{flashcardId}/{flashcardName}/{description}",
//            arguments = listOf(
//                navArgument("flashcardId") { type = NavType.IntType },
//                navArgument("flashcardName") { type = NavType.StringType },
//                navArgument("description") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val flashcardId = backStackEntry.arguments?.getInt("flashcardId")
//            val flashcardName = backStackEntry.arguments?.getString("flashcardName")
//            val description = backStackEntry.arguments?.getString("description")
//
//            if (flashcardId != null && flashcardName != null && description != null) {
//                FlashcardDetailScreen(
//                    flashcardId = flashcardId,
//                    flashcardName = flashcardName,
//                    description = description,
//                    onNavigateUp = { navController.popBackStack() },
//                    navController = navController
//                )
//            }
//        }
//
//        composable(ScreenRoutes.ReviewCardScreen.route) {
//            ReviewCardScreen(onBack = { navController.navigate(ScreenRoutes.FlashcardDetailScreen.route) })
//        }







