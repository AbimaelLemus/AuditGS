package com.example.auditgs.domain.repository

import com.example.auditgs.domain.model.User

interface AuthRepository {
    suspend fun login(
        usuario: String,
        clave: String,
        ip: String,
    ): Result<User>
}