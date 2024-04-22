package com.example.flashwiz_fe.domain.use_case

import com.example.flashwiz_fe.domain.model.ForgotPasswordInputValidationType

class ValidateForgotPasswordEmailInputUseCase() {
    operator fun invoke(email: String): ForgotPasswordInputValidationType {
        if(email.isEmpty() ){
            return ForgotPasswordInputValidationType.EmptyField
        }
        val emailPattern = Regex("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")
        if (!email.matches(emailPattern)) {
            return ForgotPasswordInputValidationType.NoEmail
        }
        return ForgotPasswordInputValidationType.Valid
    }
}