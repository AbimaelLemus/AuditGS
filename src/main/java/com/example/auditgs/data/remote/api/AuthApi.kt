package com.example.auditgs.data.remote.api

import com.example.auditgs.data.remote.dto.LoginRequestDto
import com.example.auditgs.data.remote.response.BaseResponse
import com.example.auditgs.data.remote.dto.LoginResultDto
import com.example.auditgs.data.remote.dto.LogoutRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<BaseResponse<LoginResultDto>>

    @POST("logout")
    suspend fun logout(

        @Body request: LogoutRequestDto

    ): Response<BaseResponse<Unit>>
}