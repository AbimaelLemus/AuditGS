package com.example.auditgs.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager,
    private val TAG: String = LoginViewModel::class.java.simpleName
) : ViewModel() {

    fun login(usuario: String, clave: String, ip: String) {

        viewModelScope.launch {

            val result = loginUseCase(usuario, clave, ip)

            result.onSuccess { user ->
                sessionManager.saveSession(
                    idSession = user.idSession,
                    token = user.token,
                    user = user.nombre
                )
                Log.d(TAG, "Bienvenido ${user.nombre}")
            }.onFailure {
                Log.e(TAG, it.message.orEmpty())
            }
        }
    }
}