package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.model.ResetPasswordValidationType
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.domain.use_case.ValidateResetPasswordInputUseCase
import com.example.flashwiz_fe.presentation.state.ResetPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateResetPasswordInputUseCase:  ValidateResetPasswordInputUseCase
): ViewModel(){
    var resetPasswordState by mutableStateOf(ResetPasswordState())
        private set

    fun onNewPasswordChange(newValue: String){
        resetPasswordState = resetPasswordState.copy(newPasswordInput = newValue)
        checkInputValidation()
    }
    fun onNewPasswordRepeatedInputChange(newValue: String){
        resetPasswordState = resetPasswordState.copy(newPasswordRepeatedInput = newValue)
        checkInputValidation()
    }
    fun onToggleVisualTransformationNewPassword(){
        resetPasswordState = resetPasswordState.copy(isNewPasswordShown = !resetPasswordState.isNewPasswordShown)
    }

    fun onToggleVisualTransformationNewPasswordRepeated(){
        resetPasswordState = resetPasswordState.copy(
            isNewPasswordRepeatedShown = !resetPasswordState.isNewPasswordRepeatedShown
        )
    }

    fun onChangePasswordClick() {
        resetPasswordState = resetPasswordState.copy(isLoading = true)
        viewModelScope.launch {
            resetPasswordState = try{
                val statusResult = authRepository.resetPassword(
                    newPassword = resetPasswordState.newPasswordInput
                )
                println("gia tri cua statusResult: $statusResult")
                resetPasswordState.copy(isSuccessfullyResetPassword = statusResult)
            }catch(e: Exception){
                resetPasswordState.copy(errorMessageResetPasswordProcess = "Could not change password")
            }finally {
                resetPasswordState = resetPasswordState.copy(isLoading = false)
            }
        }
    }
    private fun checkInputValidation() {
        val validationResult = validateResetPasswordInputUseCase (
            resetPasswordState.newPasswordInput,
            resetPasswordState.newPasswordRepeatedInput
        )
        processInputValidationType(validationResult)
    }
    private fun processInputValidationType(type: ResetPasswordValidationType){
        resetPasswordState = when(type){
            ResetPasswordValidationType.EmptyField -> {
                resetPasswordState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            ResetPasswordValidationType.PasswordTooShort -> {
                resetPasswordState.copy(errorMessageInput = "Password too short", isInputValid = false)
            }
            ResetPasswordValidationType.PasswordsDoNotMatch -> {
                resetPasswordState.copy(errorMessageInput = "Passwords do not match", isInputValid = false)
            }
            ResetPasswordValidationType.PasswordUpperCaseMissing -> {
                resetPasswordState.copy(errorMessageInput = "Password needs to contain at least one upper case char", isInputValid = false)
            }
            ResetPasswordValidationType.PasswordSpecialCharMissing -> {
                resetPasswordState.copy(errorMessageInput = "Password needs to contain at least one special char", isInputValid = false)
            }
            ResetPasswordValidationType.PasswordNumberMissing -> {
                resetPasswordState.copy(errorMessageInput = "Password needs to contain at least one number", isInputValid = false)
            }
            ResetPasswordValidationType.Valid -> {
                resetPasswordState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
}