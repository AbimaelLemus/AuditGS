package com.example.auditgs.domain.usecase

import com.example.auditgs.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        idSession: String
    ): Result<Unit> = repository.logout(idSession)

}