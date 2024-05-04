package com.example.flashwiz_fe.data


import android.content.Context
import com.example.flashwiz_fe.domain.model.ChangePasswordRequest
import com.example.flashwiz_fe.domain.model.ChangePasswordSuccessfully
import com.example.flashwiz_fe.domain.model.ForgotPasswordResponse
import com.example.flashwiz_fe.domain.model.LoginRequest
import com.example.flashwiz_fe.domain.model.RegisterResponse
import com.example.flashwiz_fe.domain.model.TokenResponse
import com.example.flashwiz_fe.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class AuthRepositoryImpl(context: Context) : AuthRepository {
    private val authApiService = RetrofitInstance.authApiService
    private val userPreferences = UserPreferences(context)
    private var savedOTP: String? = null
    override suspend fun login(email: String, password: String): Boolean {
        delay(5000)
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
                    val userName = tokenResponseBody.name

                    println("Lưu thông tin email và token")
                // Lưu access token vào DataStore
                    if (!accessToken.isNullOrEmpty() && !userEmail.isNullOrEmpty()) {
                        userPreferences.saveUserToken(accessToken)
                        userPreferences.saveUserEmail(userEmail)
                        userPreferences.saveIsLoggedIn(true)
                        userPreferences.saveUserId(userId)
                        userPreferences.saveUserName(userName)
                        println("Lưu thông tin token,email vào dataStore")
                        println("Thông tin Access Token: ${userPreferences.getUserToken()}")
                        println("Thông tin Email: ${userPreferences.getUserEmail()}")
                        println("Thông tin UserId: ${userPreferences.getUserId()}")
                        println("Thông tin Name: ${userPreferences.getUserName()}")

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

    override suspend fun changePassword(oldPassword: String, newPassword: String): Boolean {
        return try {
            val userEmail = userPreferences.getUserEmail()

            if (userEmail.isNullOrEmpty()) {
                println("Lỗi: Không tìm thấy email người dùng đã lưu trữ")
                return false
            }

            val requestBody = ChangePasswordRequest(userEmail, oldPassword, newPassword)
            val response: Response<ChangePasswordSuccessfully> = authApiService.changePassword(requestBody)

            if (response.isSuccessful) {
                println("Đổi mật khẩu thành công")
                return true
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Lỗi không xác định khi đổi mật khẩu"
                println("Lỗi khi đổi mật khẩu: $errorMsg")
                return false
            }
        } catch (e: Exception) {
            println("Xảy ra lỗi: ${e.message}")
            return false
        }
    }

    override suspend fun forgot(email: String): Boolean {
        return try {
            val response: Response<ForgotPasswordResponse> = authApiService.forgot(email)
            if (response.isSuccessful) {
                val forgotPasswordResponse = response.body()
                forgotPasswordResponse?.let {
                    println("Email: ${it.email}")
                    println("resetPasswordOTP: ${it.otp}")
                    savedOTP = it.otp
                    true
                } ?: run {
                    println("Không có dữ liệu trả về từ API.")
                    false
                }
            } else {
                println("API trả về mã lỗi: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Xảy ra lỗi: ${e.message}")
            false
        }
    }

    override suspend fun resetPassword(newPassword: String): Boolean {
        delay(10000)
        return try {
            if (savedOTP.isNullOrEmpty()) {
                println("Lỗi: Mã OTP không tồn tại.")
                return false
            }
            val response: Response<ChangePasswordSuccessfully> = authApiService.resetPassword(newPassword, savedOTP ?: "")
            if (response.isSuccessful) {
                println("Thay đổi mật khẩu thành công")
                true
            } else {
                println("Lỗi khi thay đổi mật khẩu: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Xảy ra lỗi: ${e.message}")
            false
        }
    }



    override suspend fun verifiedOtp(otp: String): Boolean {
        return otp == savedOTP
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