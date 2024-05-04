package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.presentation.state.ChangePassWordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository

): ViewModel(){
    var changePasswordState by mutableStateOf(ChangePassWordState())
        private set
    fun onOldPasswordInputChange(newValue: String){
        changePasswordState = changePasswordState.copy(oldPasswordInput = newValue)
    }

    fun onNewPasswordInputChange(newValue: String){
        changePasswordState = changePasswordState.copy(newPasswordInput = newValue)

    }
    fun onToggleVisualTransformation(){
        changePasswordState = changePasswordState.copy(isPasswordShown = !changePasswordState.isPasswordShown)
    }
    fun onChangePasswordClick() {
        viewModelScope.launch {
            changePasswordState = try{
                val statusResult = authRepository.changePassword(
                    oldPassword = changePasswordState.oldPasswordInput,
                    newPassword = changePasswordState.newPasswordInput
                )
                println("gia tri cua statusResult: $statusResult")
                changePasswordState.copy(isSuccessfullyChangePassword = statusResult)
            }catch(e: Exception){
                changePasswordState.copy()
            }
        }
    }
}