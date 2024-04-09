package com.example.flashwiz_fe.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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

