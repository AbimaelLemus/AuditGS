package com.example.auditgs.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
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

            val result = loginUseCase(usuario, clave, ip)

            result.onSuccess { user ->
                sessionManager.saveSession(
                    idSession = user.idSession,
                    token = user.token,
                    user = user.nombre
                )

                Log.d(TAG, "Bienvenido ${user.nombre}")
                _uiState.value = LoginUiState.Success("Bienvenido ${user.nombre}")
            }.onFailure {
                Log.e(TAG, it.message.orEmpty())
                _uiState.value = LoginUiState.Error(it.message.orEmpty())
            }
        }
    }
}