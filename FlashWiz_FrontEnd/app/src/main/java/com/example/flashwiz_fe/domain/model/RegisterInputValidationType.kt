package com.example.flashwiz_fe.domain.model

enum class RegisterInputValidationType {
    EmptyField,
    NoValidEmail,
    PasswordsDoNotMatch,
    PasswordUpperCaseMissing,
    PasswordNumberMissing,
    PasswordSpecialCharMissing,
    PasswordTooShort,
    Valid
}
