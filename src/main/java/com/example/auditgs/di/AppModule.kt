package com.example.auditgs.di

import android.content.Context
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.data.remote.api.AuthApi
import com.example.auditgs.data.repository.AuthRepositoryImpl
import com.example.auditgs.domain.repository.AuthRepository
import com.example.auditgs.domain.usecase.LoginUseCase
import com.example.auditgs.domain.usecase.LogoutUseCase
import com.example.auditgs.utils.NetworkModule

object AppModule {

    fun provideAuthApi(): AuthApi = NetworkModule.authApi

    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(provideAuthApi())

    fun provideLoginUseCase(): LoginUseCase = LoginUseCase(provideAuthRepository())

    fun provideSessionManager(
        context: Context
    ): SessionManager = SessionManager(context)

    fun provideLogoutUseCase(): LogoutUseCase = LogoutUseCase(provideAuthRepository())

}