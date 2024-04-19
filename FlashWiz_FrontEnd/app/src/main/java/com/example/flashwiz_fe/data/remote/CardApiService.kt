package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.Card
import com.example.flashwiz_fe.domain.model.CardDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CardApiService {

    @GET("/card/get-all")
    suspend fun getAllCards(): List<Card>

    @POST("/card/save")
    suspend fun saveCard(@Body card: Card): Response<Card>
    @GET("card/get-by-flashcard/{flashcardId}")
    suspend fun getCardsByFlashcardId(@Path("flashcardId") flashcardId: Int): List<CardDetail>
    @DELETE("/card/delete/{id}")
    suspend fun deleteCard(@Path("id") id: Int): List<CardDetail>


}