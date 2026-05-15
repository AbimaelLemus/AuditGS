package com.example.auditgs.data.repository

import android.util.Log
import com.example.auditgs.data.remote.api.AuthApi
import com.example.auditgs.data.remote.dto.LoginRequestDto
import com.example.auditgs.domain.model.User
import com.example.auditgs.domain.repository.AuthRepository
import com.example.auditgs.utils.Resource

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        usuario: String,
        clave: String,
        ip: String,
    ): Resource<User> {

        return try {
            val request =
                LoginRequestDto(
                    usuario = usuario,
                    dispositivo = "ANDROID_ID",
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

                    Resource.Success(
                        User(
                            nombre = result?.nombre.orEmpty(),
                            correo = result?.correo.orEmpty(),
                            token = result?.token.orEmpty(),
                            idSession = result?.idSession.orEmpty(),
                            numeroEmpleado = result?.numeroEmpleado.orEmpty()
                        )
                    )
                } else {
                    Resource.Error(
                        message =
                        body?.mensaje.orEmpty(),

                        code =
                        body?.codigo,

                        idSession =
                        body?.resultado?.idSession
                    )
                }

            } else {
                Resource.Error(message = "Error servidor")
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message.orEmpty())
        }
    }
}