package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.data.model.Flashcard
import com.example.flashwiz_fe.data.model.FlashcardDetail
import retrofit2.http.Body
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
}