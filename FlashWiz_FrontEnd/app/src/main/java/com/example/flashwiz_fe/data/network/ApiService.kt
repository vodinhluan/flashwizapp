package com.example.flashwiz_fe.data.network

import com.example.flashwiz_fe.data.model.Folder
import com.example.flashwiz_fe.data.model.LoginRequest
import com.example.flashwiz_fe.data.model.RegisterResponse
import com.example.flashwiz_fe.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("folder/get-all")
    suspend fun getAllFolders(): List<Folder> // Folder là class đại diện cho một folder
    @POST("/folder/save")
    suspend fun saveFolder(
        @Body folder: Folder, // Đối tượng Folder cần lưu
        @Query("userId") userId: Int // userId của người dùng hiện tại
    ): Folder
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Response<RegisterResponse>

    @POST("/auth/login")
    suspend fun login(
        @Body body: LoginRequest

    ): Response<TokenResponse>

}


//private fun isEmailAlreadyRegistered(email: String): Boolean {
//    // Logic kiểm tra email đã được đăng ký trước đó trong cơ sở dữ liệu
//    // Return true nếu email đã tồn tại, ngược lại return false
//    return false // Giả sử không có email nào đã được đăng ký trước đó
//}



