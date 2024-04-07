package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.data.model.Card
import retrofit2.http.GET
import retrofit2.http.Path

interface CardApiService {
    @GET("card/get-by-flashcard/{flashcardId}")
    suspend fun getCardsByFlashcardId(@Path("flashcardId") flashcardId: Int): List<Card>

}