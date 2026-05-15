package com.example.auditgs.domain.repository

import com.example.auditgs.domain.model.User
import com.example.auditgs.utils.Resource

interface AuthRepository {
    suspend fun login(
        usuario: String,
        clave: String,
        ip: String,
    ): Resource<User>
}