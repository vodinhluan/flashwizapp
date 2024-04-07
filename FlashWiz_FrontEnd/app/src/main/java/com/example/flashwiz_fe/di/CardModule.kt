package com.example.flashwiz_fe.di

import com.example.flashwiz_fe.data.CardRepositoryImpl
import com.example.flashwiz_fe.data.remote.ApiService
import com.example.flashwiz_fe.data.remote.RetrofitInstance
import com.example.flashwiz_fe.domain.repository.CardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CardModule {
//    @Provides
//    @Singleton
//    fun provideCardApi(): ApiService {
//        return RetrofitInstance.apiService
//    }

//    @Provides
//    @Singleton
//    fun provideCardRepository(apiService: ApiService): CardRepository {
//        return CardRepositoryImpl(apiService)
//    }
}

