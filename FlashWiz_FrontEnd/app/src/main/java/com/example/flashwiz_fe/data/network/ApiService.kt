package com.example.flashwiz_fe.data.network

import com.example.flashwiz_fe.data.model.Folder
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("folder/get-all")
    suspend fun getAllFolders(): List<Folder>
    @POST("/folder/save")
    suspend fun saveFolder(
        @Body folder: Folder,
        @Query("userId") userId: Int
    ): Folder
}