package com.example.flashwiz_fe.data


import com.example.flashwiz_fe.data.remote.AuthApiService
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.data.remote.FlashcardApiService
import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.data.remote.GroupApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {


    private const val BASE_URL = "http://192.168.235.161:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
    val folderApiService: FolderApiService by lazy {
        retrofit.create(FolderApiService::class.java)
    }
    val flashcardApiService: FlashcardApiService by lazy {
        retrofit.create(FlashcardApiService::class.java)
    }
    val cardApiService: CardApiService by lazy {
        retrofit.create(CardApiService::class.java)
    }
    val groupApiService: GroupApiService by lazy {
        retrofit.create(GroupApiService::class.java)
    }
}






