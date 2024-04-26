package com.example.flashwiz_fe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashwiz_fe.data.UserPreferences
import com.example.flashwiz_fe.domain.model.LoginInputValidationType
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.domain.use_case.ValidateLoginInputUseCase
import com.example.flashwiz_fe.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences

): ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set
    var userId by mutableStateOf<String?>(null)
        private set
    fun onEmailInputChange(newValue: String){
        loginState = loginState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String){
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformation(){
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    fun onLoginClick(){
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val loginResult = authRepository.login(
                    email = loginState.emailInput,
                    password = loginState.passwordInput
                )
                val newLoginState = if (loginResult) {
                    val userId = userPreferences.getUserId().toString()
                    println("${userId}")
                    this@LoginViewModel.userId = userId
                        loginState.copy(isSuccessfullyLoggedIn = true, errorMessageLoginProcess = null,userId = userId)
                    ?: throw IllegalStateException("User id is null")
                } else {
                    loginState.copy(isSuccessfullyLoggedIn = false, errorMessageLoginProcess = "Incorrect email or password")
                }
                loginState = newLoginState
            } catch(e: Exception){
                loginState = loginState.copy(isSuccessfullyLoggedIn = false, errorMessageLoginProcess = "Could not login")
            } finally {
                loginState = loginState.copy(isLoading = false)
            }
        }
    }




    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType){
        loginState = when(type){
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            LoginInputValidationType.NoEmail -> {
                loginState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }
            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
//    suspend fun getUserIdFromPreferences(): String? {
//        return authRepository.getUserIdFromPreferences()
//    }

}
