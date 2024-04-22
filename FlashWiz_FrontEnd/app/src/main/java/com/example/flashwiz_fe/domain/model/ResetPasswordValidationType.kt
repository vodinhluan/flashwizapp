package com.example.flashwiz_fe.domain.model

enum class ResetPasswordValidationType {
    EmptyField,
    PasswordsDoNotMatch,
    PasswordUpperCaseMissing,
    PasswordNumberMissing,
    PasswordSpecialCharMissing,
    PasswordTooShort,
    Valid
}


