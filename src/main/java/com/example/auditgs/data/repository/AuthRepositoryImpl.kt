package com.example.auditgs.data.repository

import android.util.Log
import com.example.auditgs.data.remote.api.AuthApi
import com.example.auditgs.data.remote.dto.LoginRequestDto
import com.example.auditgs.domain.model.User
import com.example.auditgs.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        usuario: String,
        clave: String,
        ip: String,
    ): Result<User> {

        return try {
            val request =
                LoginRequestDto(
                    usuario = usuario,
                    dispositivo = "ANDROID",
                    ip = ip,
                    transferir = false,
                    token = "GUID",
                    clave = clave
                )

            val response =
                api.login(request)

            if (response.isSuccessful) {

                val body = response.body()

                if (body?.codigo == 0) {

                    val result = body.resultado

                    Result.success(
                        User(
                            nombre = result?.nombre.orEmpty(),
                            correo = result?.correo.orEmpty(),
                            token = result?.token.orEmpty(),
                            idSession = result?.idSession.orEmpty(),
                            numeroEmpleado = result?.numeroEmpleado.orEmpty()
                        )
                    )
                } else {
                    Result.failure(
                        Exception(body?.mensaje)
                    )
                }

            } else {
                Result.failure(
                    Exception("Error servidor")
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}