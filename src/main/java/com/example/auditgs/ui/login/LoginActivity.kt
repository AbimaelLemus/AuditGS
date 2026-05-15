package com.example.auditgs.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.data.repository.AuthRepositoryImpl
import com.example.auditgs.databinding.ActivityLoginBinding
import com.example.auditgs.di.AppModule
import com.example.auditgs.domain.usecase.LoginUseCase
import com.example.auditgs.ui.agreements.AgreementsActivity
import com.example.auditgs.utils.NetworkModule
import kotlinx.coroutines.launch
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
        observeUiState()
    }

    private fun observeUiState() {

        lifecycleScope.launch {

            repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    when (state) {

                        is LoginUiState.Idle -> {

                        }

                        is LoginUiState.Loading -> {
                            binding.pbLogin.visibility = View.VISIBLE
                        }

                        is LoginUiState.Success -> {
                            binding.pbLogin.visibility = View.GONE

                            Toast.makeText(
                                this@LoginActivity, state.message, Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(this@LoginActivity, AgreementsActivity::class.java)
                            )

                            finish()
                        }

                        is LoginUiState.Error -> {
                            binding.pbLogin.visibility = View.GONE

                            Toast.makeText(
                                this@LoginActivity, state.message, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
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

        viewModel =
            LoginViewModel(
                loginUseCase = AppModule.provideLoginUseCase(),
                logoutUseCase = AppModule.provideLogoutUseCase(),
                sessionManager = AppModule.provideSessionManager(this)
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