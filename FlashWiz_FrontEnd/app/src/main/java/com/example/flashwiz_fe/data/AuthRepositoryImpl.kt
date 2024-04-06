package com.example.flashwiz_fe.data

import com.example.flashwiz_fe.data.model.RegisterResponse
import com.example.flashwiz_fe.data.network.RetrofitInstance
import com.example.flashwiz_fe.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import retrofit2.Response
class AuthRepositoryImpl : AuthRepository {
    private val apiService = RetrofitInstance.apiService
    override suspend fun login(email: String, password: String): Boolean {
        delay(1000)
//        try {
////            // Gọi API đăng nhập người dùng
//            val response: Response<TokenResponse> = apiService.login(email, password)
////
////            // Kiểm tra kết quả từ response
//            if (response.isSuccessful) {
////                // Lấy token từ response
//                val tokenResponse: TokenResponse? = response.body()
//                println("Ket noi nguoi dung thanh cong")
//
////
////                // Kiểm tra tokenResponse không null
//                if (tokenResponse != null) {
//                    val accessToken = tokenResponse.accessToken
//                    val email = tokenResponse.email
////
////                    //Kiểm tra tokenResponse không null
//                    if (!accessToken.isNullOrEmpty() && !email.isNullOrEmpty()) {
//                        // Lưu token và email vào lưu trữ để ghi nhớ đăng nhập (ví dụ: SharedPreferences)
//                        // saveTokenAndEmail(accessToken, email)
//                        return true // Đăng nhập thành công
//                    }
//                } else {
//                    return false
//                }
//            } else {
//                // Xử lý trường hợp gọi API không thành công
//                return false
//            }
//        } catch (e: Exception) {
//            // Xử lý các trường hợp lỗi khác nhau (ví dụ: lỗi kết nối, lỗi parsing response, ...)
//            println("Xảy ra lỗi: ${e.message}")
//            return false
//        }
    return true
    }
    override suspend fun register(name: String, email: String, password: String): Boolean {
        delay(1000)
        return try {
            val response: Response<RegisterResponse> = apiService.register(name, email, password)
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
