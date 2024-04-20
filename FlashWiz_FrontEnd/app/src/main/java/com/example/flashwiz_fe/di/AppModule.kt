package com.example.flashwiz_fe.di

import android.content.Context
import com.example.flashwiz_fe.data.AuthRepositoryImpl
import com.example.flashwiz_fe.data.CardRepositoryImpl
import com.example.flashwiz_fe.data.RetrofitInstance
import com.example.flashwiz_fe.data.remote.CardApiService
import com.example.flashwiz_fe.domain.repository.AuthRepository
import com.example.flashwiz_fe.domain.repository.CardRepository
import com.example.flashwiz_fe.domain.use_case.ValidateForgotPasswordEmailInputUseCase
import com.example.flashwiz_fe.domain.use_case.ValidateLoginInputUseCase
import com.example.flashwiz_fe.domain.use_case.ValidateOTPInputUseCase
import com.example.flashwiz_fe.domain.use_case.ValidateRegisterInputUseCase
import com.example.flashwiz_fe.domain.use_case.ValidateResetPasswordInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase():ValidateRegisterInputUseCase{
        return ValidateRegisterInputUseCase()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository {
        return AuthRepositoryImpl(context)
    }
    @Provides
    @Singleton
    fun provideValidateForgotPasswordEmailInputUseCase(): ValidateForgotPasswordEmailInputUseCase {
        return ValidateForgotPasswordEmailInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateOTPInputUseCase(): ValidateOTPInputUseCase {
        return ValidateOTPInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateResetPasswordUseCase(): ValidateResetPasswordInputUseCase {
        return ValidateResetPasswordInputUseCase()
    }

    // Card Module
    @Provides
    @Singleton
    fun provideCardApi(): CardApiService {
        return RetrofitInstance.cardApiService
    }

    @Provides
    @Singleton
    fun provideCardRepository(cardApiService: CardApiService): CardRepository {
        return CardRepositoryImpl(cardApiService)
    }

}


