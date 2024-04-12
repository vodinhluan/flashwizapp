package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.Flashcard
import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.domain.model.FolderDetail
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FlashcardApiService {
    @GET("flashcard/get-by-folder/{folderId}")
    suspend fun getFlashcardsByFolderId(@Path("folderId") folderId: Int): List<FlashcardDetail>
    @POST("/flashcard/save")
    suspend fun saveFlashcard(
        @Body flashcard: Flashcard,

        @Query("userId") userId: Int,
        @Query("folderId") folderId: Int
    ): Flashcard
    @DELETE("/flashcard/delete/{id}")
    suspend fun deleteFlashcard(@Path("id") id: Int): List<FlashcardDetail>
}