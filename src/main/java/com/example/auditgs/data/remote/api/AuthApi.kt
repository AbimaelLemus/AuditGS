package com.example.auditgs.data.remote.api

import com.example.auditgs.data.remote.dto.LoginRequestDto
import com.example.auditgs.data.remote.response.BaseResponse
import com.example.auditgs.data.remote.dto.LoginResultDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<BaseResponse<LoginResultDto>>
}