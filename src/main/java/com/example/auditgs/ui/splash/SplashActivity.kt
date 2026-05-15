package com.example.auditgs.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.auditgs.data.local.SessionManager
import com.example.auditgs.databinding.ActivitySplashBinding
import com.example.auditgs.ui.agreements.AgreementsActivity
import com.example.auditgs.ui.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val TAG = SplashActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateSession()

    }

    private fun validateSession() {
        lifecycleScope.launch {

            delay(2_000)

            val sessionManager =
                SessionManager(this@SplashActivity)

            val idSession =
                sessionManager.getIdSession()

            Log.d(TAG, "onCreate: $idSession")

            if (idSession != null) {
                startActivity(Intent(this@SplashActivity, AgreementsActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, AgreementsActivity::class.java))
            }

            finish()
        }
    }
}