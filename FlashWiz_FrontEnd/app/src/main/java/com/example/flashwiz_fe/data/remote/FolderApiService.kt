package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.data.model.Folder
import com.example.flashwiz_fe.data.model.FolderDetail
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FolderApiService {
    @GET("folder/get-all")
    suspend fun getAllFolders(): List<FolderDetail>

    @POST("/folder/save")
    suspend fun saveFolder(
        @Body folder: Folder,
        @Query("userId") userId: Int
    ): Folder
}