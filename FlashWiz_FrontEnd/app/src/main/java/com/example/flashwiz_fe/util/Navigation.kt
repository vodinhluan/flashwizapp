package com.example.flashwiz_fe.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashwiz_fe.presentation.screen.LoginScreen
import com.example.flashwiz_fe.presentation.screen.MainScreen
import com.example.flashwiz_fe.presentation.screen.RegisterScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.LoginScreen.route
    ){
        composable(ScreenRoutes.LoginScreen.route){
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.MainScreen.route){
                        popUpTo(0)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(ScreenRoutes.RegisterScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.RegisterScreen.route){
            RegisterScreen(
                onRegisterSuccessNavigation = {
                    navController.navigate(ScreenRoutes.MainScreen.route){
                        popUpTo(0)
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(ScreenRoutes.LoginScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(ScreenRoutes.MainScreen.route){
            MainScreen()
        }
    }

}

sealed class ScreenRoutes(val route:String){
    object LoginScreen:ScreenRoutes("login_screen")
    object RegisterScreen:ScreenRoutes("register_screen")
    object MainScreen:ScreenRoutes("main_screen")
}