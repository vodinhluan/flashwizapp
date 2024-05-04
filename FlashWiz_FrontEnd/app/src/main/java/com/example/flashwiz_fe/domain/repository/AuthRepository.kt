package com.example.flashwiz_fe.domain.repository

import android.media.session.MediaSession.Token
import com.example.flashwiz_fe.domain.model.TokenResponse
import com.example.flashwiz_fe.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password:String):Boolean
    suspend fun register(name: String, email:String, password: String):Boolean
    suspend fun logout()

    //    suspend fun getUserIdFromPreferences(): String?
    suspend fun forgot(email: String):Boolean
    suspend fun verifiedOtp(otp: String):Boolean
    suspend fun resetPassword(newPassword: String): Boolean

    suspend fun getUserById_Token(id: Int) : TokenResponse
    suspend fun getUserById(id: Int): User

    suspend fun changePassword(oldPassword: String, newPassword: String): Boolean}