package com.example.flashwiz_fe.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password:String):Boolean
    suspend fun register(name: String, email:String, password: String):Boolean
    suspend fun logout()
    suspend fun forgot(email: String):Boolean
    suspend fun verifiedOtp(otp: String):Boolean
    suspend fun changePassword(newPassword: String): Boolean
}
