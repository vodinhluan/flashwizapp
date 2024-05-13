package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.model.ForgotPasswordInputValidationType
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.domain.use_case.ValidateForgotPasswordEmailInputUseCase
import com.example.flashwiz_fe.presentation.state.ForgotPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateForgotPasswordEmailInputUseCase: ValidateForgotPasswordEmailInputUseCase
): ViewModel() {
    var forgotPasswordState by mutableStateOf(ForgotPasswordState())
        private set

    fun onEmailInputChange(newValue: String){
        forgotPasswordState = forgotPasswordState.copy(emailInput = newValue)
        checkInputValidation()
    }
    fun onSendEmailClick() {
        forgotPasswordState = forgotPasswordState.copy(isLoading = true)
        viewModelScope.launch {
            forgotPasswordState = try{

                val statusResult = authRepository.forgot(
                    email = forgotPasswordState.emailInput
                )

                forgotPasswordState.copy(isSuccessfullySendEmail = statusResult)
            }catch(e: Exception){
                forgotPasswordState.copy(errorMessageSendEmailProcess = "Could not send email")
            }finally {
                forgotPasswordState = forgotPasswordState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation(){
        val validationResult = validateForgotPasswordEmailInputUseCase(
            forgotPasswordState.emailInput,
        )
        processInputValidationType(validationResult)
    }
    private fun processInputValidationType(type: ForgotPasswordInputValidationType){
        forgotPasswordState = when(type){
            ForgotPasswordInputValidationType.EmptyField -> {
                forgotPasswordState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            ForgotPasswordInputValidationType.NoEmail -> {
                forgotPasswordState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }
            ForgotPasswordInputValidationType.Valid -> {
                forgotPasswordState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }


}
