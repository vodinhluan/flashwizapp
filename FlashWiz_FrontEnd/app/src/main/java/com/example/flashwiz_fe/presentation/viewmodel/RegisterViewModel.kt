package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.domain.model.RegisterInputValidationType
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.domain.use_case.ValidateRegisterInputUseCase
import com.example.flashwiz_fe.presentation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val authRepository: AuthRepository
): ViewModel() {

    var registerState by mutableStateOf(RegisterState())
        private set
    fun onNameInputChange(newValue: String){
        registerState = registerState.copy(nameInput = newValue)
        checkInputValidation()
    }

    fun onEmailInputChange(newValue: String){
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String){
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onPasswordRepeatedInputChange(newValue: String){
        registerState = registerState.copy(passwordRepeatedInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformationPassword(){
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordShown)
    }

    fun onToggleVisualTransformationPasswordRepeated(){
        registerState = registerState.copy(
            isPasswordRepeatedShown = !registerState.isPasswordRepeatedShown
        )
    }
//    Backup Register Code
//    fun onRegisterClick(){
//        registerState = registerState.copy(isLoading = true)
//        viewModelScope.launch {
//            registerState = try{
//                val registerResult = authRepository.register(
//                    name = registerState.nameInput,
//                    email = registerState.emailInput,
//                    password = registerState.passwordInput
//                )
//                registerState.copy(isSuccessfullyRegistered = registerResult)
//            }catch(e: Exception){
//                registerState.copy(errorMessageRegisterProcess = "Could not login")
//            }finally {
//                registerState = registerState.copy(isLoading = false)
//            }
//        }
//    }
    fun onRegisterClick() {
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val result = authRepository.register(
                    name = registerState.nameInput,
                    email = registerState.emailInput,
                    password = registerState.passwordInput
                )
                registerState = registerState.copy(
                    isSuccessfullyRegistered = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                registerState = registerState.copy(
                    errorMessageRegisterProcess = "Không thể đăng ký",
                    isLoading = false
                )
            }
        }
    }



    private fun checkInputValidation(){
        val validationResult = validateRegisterInputUseCase(
            registerState.nameInput,
            registerState.emailInput,
            registerState.passwordInput,
            registerState.passwordRepeatedInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType){
        registerState = when(type){
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            RegisterInputValidationType.NoValidEmail-> {
                registerState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }
            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(errorMessageInput = "Password too short", isInputValid = false)
            }
            RegisterInputValidationType.PasswordsDoNotMatch -> {
                registerState.copy(errorMessageInput = "Passwords do not match", isInputValid = false)
            }
            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(errorMessageInput = "Password needs to contain at least one upper case char", isInputValid = false)
            }
            RegisterInputValidationType.PasswordSpecialCharMissing -> {
                registerState.copy(errorMessageInput = "Password needs to contain at least one special char", isInputValid = false)
            }
            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(errorMessageInput = "Password needs to contain at least one number", isInputValid = false)
            }
            RegisterInputValidationType.Valid -> {
                registerState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }

}
