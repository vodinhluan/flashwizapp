package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.ChangePasswordSuccessfully
import com.example.flashwiz_fe.domain.model.ForgotPasswordResponse
import com.example.flashwiz_fe.domain.model.LoginRequest
import com.example.flashwiz_fe.domain.model.RegisterResponse
import com.example.flashwiz_fe.domain.model.TokenResponse
import com.example.flashwiz_fe.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>

    @POST("/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST("/forgot_password")
    suspend fun forgot(
        @Field("email") email: String,
    ): Response<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST("/reset_password")
    suspend fun changePassword(
        @Field("password") newPassword: String,
        @Field("otp") savedOTP: String,
    ): Response<ChangePasswordSuccessfully>

    @GET("/user/get/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

}