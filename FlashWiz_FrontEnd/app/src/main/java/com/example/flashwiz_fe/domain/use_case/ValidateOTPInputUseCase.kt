package com.example.flashwiz_fe.domain.use_case

import com.example.flashwiz_fe.domain.model.OTPInputValidationType

class ValidateOTPInputUseCase() {
    operator fun invoke(otp: String): OTPInputValidationType {
        if(otp.isEmpty()){
            return OTPInputValidationType.EmptyField
        }
        // Kiểm tra mã OTP chỉ chứa số
        val regex = Regex("^[0-9]{6}$")
        if (!regex.matches(otp)) {
            return OTPInputValidationType.InvalidFormat
        }
        return OTPInputValidationType.Valid
    }
}