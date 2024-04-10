package com.example.flashwiz_fe.data


import com.example.flashwiz_fe.domain.model.LoginRequest
import com.example.flashwiz_fe.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import retrofit2.Response


import com.example.flashwiz_fe.domain.model.RegisterResponse
import com.example.flashwiz_fe.domain.model.TokenResponse


class AuthRepositoryImpl : AuthRepository {
    private val authApiService = RetrofitInstance.authApiService
    override suspend fun login(email: String, password: String): Boolean {
        delay(1000)
         return try {
             // Gọi API đăng nhập người dùng
             val requestBody = LoginRequest(email, password)
             val response: Response<TokenResponse> = authApiService.login(requestBody)

            // Kiểm tra kết quả từ response
            if (response.isSuccessful) {
                val tokenResponse = response.body()
                println("Ket noi nguoi dung thanh cong")

                // Kiểm tra tokenResponse không null
                if (tokenResponse != null) {
                    val accessToken = tokenResponse.accessToken
                    val userEmail = tokenResponse.email
                    println("Lưu thông tin email và authtoken")
                    if (!accessToken.isNullOrEmpty() && !userEmail.isNullOrEmpty()) {

                        // Lưu access token vào DataStore
//                        UserPreferences.saveUserToken(context, tokenResponse)
//
//                        // Lưu preference ghi nhớ đăng nhập nếu được yêu cầu
//                        if (íLoggin) {
//                            UserPreferences.saveRememberMePreference(context, true)
//                        }

                        println("Thông tin Email: $userEmail")
                        println("Thông tin Access Token: $accessToken")
                        true // Đăng nhập thành công
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
            // Xử lý các trường hợp lỗi khác nhau (ví dụ: lỗi kết nối, lỗi parsing response, ...)
            println("Xảy ra lỗi: ${e.message}")
            return false
        }

    }
    override suspend fun register(name: String, email: String, password: String): Boolean {
        delay(1000)
        return try {
            val response: Response<RegisterResponse> = authApiService.register(name, email, password)
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
