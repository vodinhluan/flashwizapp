package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.model.OTPInputValidationType
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.domain.use_case.ValidateOTPInputUseCase
import com.example.flashwiz_fe.presentation.state.InsertOTPState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertOTPViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateOTPInputUseCase: ValidateOTPInputUseCase
):ViewModel(){
    var insertOTPState by mutableStateOf(InsertOTPState())
        private set
    fun onOTPInputChange(newValue: String){
        insertOTPState = insertOTPState.copy(otpInput = newValue)
        checkInputValidation()
    }
    private fun checkInputValidation() {
        val validationResult = validateOTPInputUseCase (
            insertOTPState.otpInput
        )
        processInputValidationType(validationResult)
    }
    fun onVerifiedOTPClick() {
        insertOTPState = insertOTPState.copy(isLoading = true)
        viewModelScope.launch {
            insertOTPState = try{
                val statusResult = authRepository.verifiedOtp(
                    otp = insertOTPState.otpInput
                )
                println("gia tri cua statusResult: $statusResult")
                insertOTPState.copy(isSuccessfullyVerifyOTP = statusResult)
            }catch(e: Exception){
                insertOTPState.copy(errorMessageVerifyOTPProcess = "Could not verified")
            }finally {
                insertOTPState = insertOTPState.copy(isLoading = false)
            }
        }
    }
    private fun processInputValidationType(type: OTPInputValidationType){
        insertOTPState = when(type){
            OTPInputValidationType.EmptyField -> {
                insertOTPState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            OTPInputValidationType.InvalidFormat -> {
                insertOTPState.copy(errorMessageInput = "InvalidOTP", isInputValid = false)
            }
            OTPInputValidationType.Valid -> {
                insertOTPState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
}