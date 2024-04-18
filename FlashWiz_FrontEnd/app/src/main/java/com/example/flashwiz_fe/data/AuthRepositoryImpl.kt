package com.example.flashwiz_fe.data


import android.content.Context
import com.example.flashwiz_fe.domain.model.LoginRequest
import com.example.flashwiz_fe.domain.model.RegisterResponse
import com.example.flashwiz_fe.domain.model.TokenResponse
import com.example.flashwiz_fe.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import retrofit2.Response

class AuthRepositoryImpl(context: Context) : AuthRepository {
    private val authApiService = RetrofitInstance.authApiService
    private val userPreferences = UserPreferences(context)

    override suspend fun login(email: String, password: String): Boolean {
        delay(1000)
        return try {
            val requestBody = LoginRequest(email, password)
            val response: Response<TokenResponse> = authApiService.login(requestBody)

            if (response.isSuccessful) {
                val tokenResponseBody = response.body()
                println("Ket noi nguoi dung thanh cong")

                if (tokenResponseBody != null) {
                    val accessToken = tokenResponseBody.accessToken
                    val userEmail = tokenResponseBody.email
                    val userId = tokenResponseBody.id

                    println("Lưu thông tin email và token")
                    if (!accessToken.isNullOrEmpty() && !userEmail.isNullOrEmpty() ) {
                        userPreferences.saveUserToken(accessToken)
                        userPreferences.saveUserEmail(userEmail)
                        userPreferences.saveIsLoggedIn(true)
                        userPreferences.saveUserId(userId)
                        println("Lưu thông tin token,email vào dataStore")
                        println("Thông tin Access Token: ${userPreferences.getUserToken()}")
                        println("Thông tin Email: ${userPreferences.getUserEmail()}")
                        println("Thông tin UserId: ${userPreferences.getUserId()}")
                        true
                    } else {
                        println("Lỗi: Thiếu dữ liệu token hoặc email")
                        false
                    }
                } else {
                    // Xử lý trường hợp response body null
                    println("Lỗi: Không có thông tin người dùng")
                    false
                }
            } else {
                println("Lỗi khi kết nối API: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Xảy ra lỗi: ${e.message}")
            return false
        }

    }

    override suspend fun logout() {
        userPreferences.clearData()
    }

    override suspend fun register(name: String, email: String, password: String): Boolean {
        delay(1000)
        return try {
            val response: Response<RegisterResponse> =
                authApiService.register(name, email, password)
            if (response.isSuccessful) {
                println("API đăng ký người dùng thành công")
                true
            } else {
                println("API đăng ký người dùng không thành công, mã lỗi: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Xảy ra lỗi: ${e.message}")
            false
        }
    }
}