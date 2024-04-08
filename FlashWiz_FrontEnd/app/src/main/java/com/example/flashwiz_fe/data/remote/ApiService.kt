package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.data.model.Card
import com.example.flashwiz_fe.data.model.Flashcard
import com.example.flashwiz_fe.data.model.FlashcardDetail
import com.example.flashwiz_fe.data.model.Folder
import com.example.flashwiz_fe.data.model.FolderDetail
import com.example.flashwiz_fe.data.model.RegisterResponse
import com.example.flashwiz_fe.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>
    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<TokenResponse>
}