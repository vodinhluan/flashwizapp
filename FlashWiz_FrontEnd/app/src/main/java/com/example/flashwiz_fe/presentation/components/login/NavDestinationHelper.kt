package com.example.flashwiz_fe.presentation.components.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NavDestinationHelper(
    shouldNavigate:()->Boolean,
    destination: (Any?) -> Unit
) {
    LaunchedEffect(key1 = shouldNavigate()){
        if(shouldNavigate()){
            destination(null)
        }
    }
}
