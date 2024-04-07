package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.Flashcard
import com.example.flashwiz_fe.domain.model.Folder
import com.example.flashwiz_fe.domain.model.FolderDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("folder/get-all")
    suspend fun getAllFolders(): List<FolderDetail>

    @POST("/folder/save")
    suspend fun saveFolder(
        @Body folder: Folder,
        @Query("userId") userId: Int
    ): Folder

    @GET("flashcard/get-by-folder/{folderId}")
    suspend fun getFlashcardsByFolderId(@Path("folderId") folderId: Int): List<Flashcard>

    @POST("/card/save")
    suspend fun saveCard(@Body card: Card): Response<Card>

}