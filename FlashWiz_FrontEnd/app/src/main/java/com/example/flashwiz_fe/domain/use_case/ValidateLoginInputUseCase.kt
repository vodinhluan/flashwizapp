package com.example.flashwiz_fe.domain.use_case

import com.example.flashwiz_fe.domain.model.LoginInputValidationType

class ValidateLoginInputUseCase() {
    operator fun invoke(email: String, password:String):LoginInputValidationType{
        if(email.isEmpty() || password.isEmpty()){
            return LoginInputValidationType.EmptyField
        }
        val emailPattern = Regex("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")
        if (!email.matches(emailPattern)) {
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }

}
