package com.example.auditgs.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.domain.usecase.LoginUseCase
import com.example.auditgs.domain.usecase.LogoutUseCase
import com.example.auditgs.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sessionManager: SessionManager,
    private val TAG: String = LoginViewModel::class.java.simpleName
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<LoginUiState>(
            LoginUiState.Idle
        )
    val uiState =
        _uiState.asStateFlow()

    fun login(usuario: String, clave: String, ip: String) {

        viewModelScope.launch {

            _uiState.value =
                LoginUiState.Loading

            when (
                val result = loginUseCase(usuario, clave, ip)
            ) {
                is Resource.Success -> {
                    val user = result.data
                    sessionManager.saveSession(
                        idSession = user.idSession,
                        token = user.token,
                        user = user.nombre
                    )

                    Log.d(TAG, "Bienvenido ${user.nombre}")
                    _uiState.value = LoginUiState.Success("Bienvenido ${user.nombre}")
                }

                is Resource.Error -> {
                    Log.e(TAG, result.message)
                    //_uiState.value = LoginUiState.Error(result.message)

                    val idSession = result.idSession
                    if (!idSession.isNullOrEmpty()) {
                        logoutPreviousSession(idSession)
                    } else {
                        _uiState.value = LoginUiState.Error(result.message)
                    }
                }
            }
        }
    }

    private suspend fun logoutPreviousSession(idSession: String) {
        val logoutResult =
            logoutUseCase(idSession)

        logoutResult.onSuccess {
            _uiState.value =
                LoginUiState.Error(
                    "La sesión anterior fue cerrada. Intenta nuevamente."
                )

        }.onFailure {

            _uiState.value =
                LoginUiState.Error(
                    it.message.orEmpty()
                )

        }
    }
}