package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.RegisterResponse
import com.example.flashwiz_fe.domain.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
//    @GET("folder/get-all")
//    suspend fun getAllFolders(): List<FolderDetail>
//
//    @POST("/folder/save")
//    suspend fun saveFolder(
//        @Body folder: Folder,
//        @Query("userId") userId: Int
//    ): Folder
//
//    @GET("flashcard/get-by-folder/{folderId}")
//    suspend fun getFlashcardsByFolderId(@Path("folderId") folderId: Int): List<Flashcard>
//
//    @POST("/card/save")
//    suspend fun saveCard(@Body card: Card): Response<Card>

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