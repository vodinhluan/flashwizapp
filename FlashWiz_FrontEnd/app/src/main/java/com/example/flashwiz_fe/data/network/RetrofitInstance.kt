package com.example.flashwiz_fe.data.network

import com.example.flashwiz_fe.data.model.Folder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.1.228:8000/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}



interface ApiService {
    @GET("folder/get-all")
    suspend fun getAllFolders(): List<Folder> // Folder là class đại diện cho một folder
}