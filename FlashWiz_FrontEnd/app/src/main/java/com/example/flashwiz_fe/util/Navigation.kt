@file:Suppress("NAME_SHADOWING")

package com.example.flashwiz_fe.util

import AddFolderScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashwiz_fe.data.CardRepositoryImpl
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.presentation.screen.MainScreen
import com.example.flashwiz_fe.presentation.screen.ReviewCardScreen
import com.example.flashwiz_fe.presentation.screen.auth.ForgotPasswordScreen
import com.example.flashwiz_fe.presentation.screen.auth.InsertOTPScreen
import com.example.flashwiz_fe.presentation.screen.auth.LoginScreen
import com.example.flashwiz_fe.presentation.screen.auth.RegisterScreen
import com.example.flashwiz_fe.presentation.screen.auth.ResetPasswordScreen
import com.example.flashwiz_fe.presentation.screen.card.AddCardScreen
import com.example.flashwiz_fe.presentation.screen.flashcard.AddFlashcardScreen
import com.example.flashwiz_fe.presentation.viewmodel.CardViewModel
import androidx.compose.runtime.LaunchedEffect as LaunchedEffect

@Composable
fun Navigation(darkTheme: Boolean, onToggleTheme: () -> Unit) {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showHeader: MutableState<Boolean>
    val userPreferences = remember { UserPreferences(context) }

    LaunchedEffect(Unit) {
        println("Email đang đăng nhập: ${userPreferences.getUserEmail()}")
        println("User Id đang đăng nhập: ${userPreferences.getUserId()}")

        if (userPreferences.getIsLoggedIn()) {
            val userId = userPreferences.getUserId() ?: "0"
            navController.navigate("${ScreenRoutes.MainScreen.route}/$userId") {
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
                onLoginSuccessNavigation = { userId ->
                    println("UserId login: $userId")
                    navController.navigate("${ScreenRoutes.MainScreen.route}/$userId") {
                        popUpTo(0)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(ScreenRoutes.RegisterScreen.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToForgotPasswordScreen = {
                    navController.navigate(ScreenRoutes.ForgotPasswordScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                onForgotPasswordSuccessNavigation = {
                    navController.navigate(ScreenRoutes.InsertOTPScreen.route) {
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
        composable(ScreenRoutes.InsertOTPScreen.route) {
            InsertOTPScreen(
                onVerifiedOTPSuccessNavigation = {
                    navController.navigate(ScreenRoutes.ResetPasswordScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.ResetPasswordScreen.route) {
            ResetPasswordScreen(
                onChangePasswordSuccessNavigation = {
                    navController.navigate(ScreenRoutes.LoginScreen.route) {
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
        composable(ScreenRoutes.MainScreen.route + "/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: "0"
            val userIdInt = if (userId.matches("\\d+".toRegex())) {
                userId.toInt()
            } else {
                0
            }
            MainScreen(navController, userIdInt)
        }

        composable(ScreenRoutes.AddFolderScreen.route + "/{userId}") { backStackEntry ->
            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                Log.d("Navigation", "Navigated to ${destination.route}")
            }
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            AddFolderScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                initialUserId = userId,
                navController = navController
            )
        }

        composable(ScreenRoutes.AddFlashcardScreen.route + "/{folderId}") { backStackEntry ->
            val folderId = backStackEntry.arguments?.getString("folderId")?.toIntOrNull()
            AddFlashcardScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                initialFolderId = folderId,
                navController = navController
            )
        }



        composable(
            route = "${ScreenRoutes.ReviewCardScreen.route}/{flashcardId}",
            arguments = listOf(navArgument("flashcardId") { type = NavType.IntType })
        ) { backStackEntry ->
            val flashcardId = backStackEntry.arguments?.getInt("flashcardId") ?: 0
            val cardViewModel: CardViewModel = hiltViewModel()
            ReviewCardScreen(cardViewModel, flashcardId)
        }


        composable(ScreenRoutes.AddCardScreen.route + "/{flashcardId}") { backStackEntry ->
            val flashcardId = backStackEntry.arguments?.getString("flashcardId")?.toIntOrNull()

            val cardViewModel: CardViewModel = remember {
                val cardRepository: CardRepository =
                    CardRepositoryImpl(RetrofitInstance.cardApiService)

                CardViewModel(cardRepository)
            } ?: error("Cannot create CardViewModel")
            AddCardScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                cardViewModel = cardViewModel,
                navController = navController,
                initialFlashcardId = flashcardId
            )
        }




        composable(ScreenRoutes.ReviewCardScreen.route) {
//            ReviewCardScreen() Phu Le comment
        }

        composable(ScreenRoutes.NotificationScreen.route) {
        }

    }
}











