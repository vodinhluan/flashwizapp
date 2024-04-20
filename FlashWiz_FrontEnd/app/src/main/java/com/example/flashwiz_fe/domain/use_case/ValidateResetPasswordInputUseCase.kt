package com.example.flashwiz_fe.domain.use_case


import com.example.flashwiz_fe.domain.model.ResetPasswordValidationType
import com.example.flashwiz_fe.util.containsNumber
import com.example.flashwiz_fe.util.containsSpecialChar
import com.example.flashwiz_fe.util.containsUpperCase

class ValidateResetPasswordInputUseCase() {
    operator fun invoke(
        newPassword: String,
        newPasswordRepeated: String
    ): ResetPasswordValidationType {

        if( newPassword.isEmpty() || newPasswordRepeated.isEmpty()){
            return ResetPasswordValidationType.EmptyField
        }
        if(newPassword!= newPasswordRepeated){
            return ResetPasswordValidationType.PasswordsDoNotMatch
        }
        if(newPassword.count() < 8){
            return ResetPasswordValidationType.PasswordTooShort
        }
        if(!newPassword.containsNumber()){
            return ResetPasswordValidationType.PasswordNumberMissing
        }
        if(!newPassword.containsUpperCase()){
            return ResetPasswordValidationType.PasswordUpperCaseMissing
        }
        if(!newPassword.containsSpecialChar()){
            return ResetPasswordValidationType.PasswordSpecialCharMissing
        }
        return ResetPasswordValidationType.Valid
    }
}