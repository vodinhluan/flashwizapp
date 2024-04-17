package com.example.flashwiz_fe.util
sealed class ScreenRoutes(val route: String) {
    object LoginScreen : ScreenRoutes("login_screen")
    object RegisterScreen : ScreenRoutes("register_screen")
    object MainScreen : ScreenRoutes("main_screen")
    object HomeScreen : ScreenRoutes("home_screen")
    object AddFolderScreen : ScreenRoutes("add_folder_screen")
    object AddCardScreen : ScreenRoutes("add_card_screen")
    object AddFlashcardScreen : ScreenRoutes("add_flashcard_screen")
    object FlashcardDetailScreen : ScreenRoutes("flashcard_detail_screen")
    object AccountScreen : ScreenRoutes("account_screen")


    object ReviewCardScreen : ScreenRoutes("review_card_screen")

    object NotificationScreen: ScreenRoutes("notification_screen")

}
