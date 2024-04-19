package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.presentation.state.LogoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    var logoutState by mutableStateOf(LogoutState())
        private set

    fun logoutClick() {
        viewModelScope.launch {
            try {
                authRepository.logout()
                logoutState = logoutState.copy(isSuccessfullyLoggedOut = true)
            }catch (e: Exception) {
                // Xử lý lỗi nếu cần
            }
        }
    }
}