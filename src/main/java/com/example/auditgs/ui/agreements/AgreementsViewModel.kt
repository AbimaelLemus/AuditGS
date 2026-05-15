package com.example.auditgs.ui.agreements

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class AgreementsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val sessionManager: SessionManager,
    private val TAG: String = AgreementsViewModel::class.java.simpleName
) : ViewModel() {

    fun logout(
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {

            val idSession =
                sessionManager.getIdSession()

            if (idSession == null) {
                onSuccess()
                return@launch
            }

            val result = logoutUseCase(idSession)

            result.onSuccess {
                sessionManager.clearSession()
                onSuccess()
            }.onFailure {
                Log.e(TAG, it.message.orEmpty())
            }
        }
    }
}