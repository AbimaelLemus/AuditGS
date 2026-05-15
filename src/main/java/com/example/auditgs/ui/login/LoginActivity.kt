package com.example.auditgs.ui.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.data.repository.AuthRepositoryImpl
import com.example.auditgs.databinding.ActivityLoginBinding
import com.example.auditgs.domain.usecase.LoginUseCase
import com.example.auditgs.utils.NetworkModule
import java.net.NetworkInterface
import java.util.Collections

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        listener()
    }

    private fun listener() {
        binding.btnLogin.setOnClickListener {
            val user = binding.etLoginUser.text.toString()
            val key = binding.etLoginKey.text.toString()
            if (user.isNotEmpty() && key.isNotEmpty()) {
                Log.d(TAG, "onCreate: $user $key ${obtenerIpLocal()}")
                viewModel.login(
                    usuario = user,
                    clave = key,
                    ip = obtenerIpLocal()
                )
            }
        }
    }

    private fun initViewModel() {

        val api =
            NetworkModule.authApi

        val repository =
            AuthRepositoryImpl(api)

        val useCase =
            LoginUseCase(repository)

        val sessionManager =
            SessionManager(this)

        viewModel =
            LoginViewModel(
                useCase,
                sessionManager
            )
    }

    fun obtenerIpLocal(): String {

        val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())

        for (networkInterface in interfaces) {

            val addresses = Collections.list(networkInterface.inetAddresses)

            for (address in addresses) {

                if (!address.isLoopbackAddress &&
                    address.hostAddress?.contains(":") == false
                ) {

                    return address.hostAddress ?: ""
                }
            }
        }

        return ""
    }
}