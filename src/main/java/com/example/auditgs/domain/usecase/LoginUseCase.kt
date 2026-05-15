package com.example.auditgs.domain.usecase

import com.example.auditgs.domain.model.User
import com.example.auditgs.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(usuario: String, clave: String, ip: String,): Result<User> {
        return repository.login(usuario, clave, ip)
    }
}