package com.example.flashwiz_fe.domain.use_case

import com.example.flashwiz_fe.domain.model.RegisterInputValidationType

import com.example.flashwiz_fe.util.containsNumber
import com.example.flashwiz_fe.util.containsSpecialChar
import com.example.flashwiz_fe.util.containsUpperCase

class ValidateRegisterInputUseCase {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType {

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()){
            return RegisterInputValidationType.EmptyField
        }
        val emailPattern = Regex("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")
        if (!email.matches(emailPattern)) {
            return RegisterInputValidationType.NoValidEmail
        }
        if(password!= passwordRepeated){
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if(password.count() < 8){
            return RegisterInputValidationType.PasswordTooShort
        }
        if(!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if(!password.containsUpperCase()){
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if(!password.containsSpecialChar()){
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        return RegisterInputValidationType.Valid
    }
}
