package com.example.flashwiz_fe.data.remote.card

import com.example.flashwiz_fe.data.model.Card
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CardApi {
    @GET("/card/get-all")
    suspend fun getAllCards(): List<Card>

    @POST("/card/save")
    suspend fun saveCard(@Body card: Card): Card
}