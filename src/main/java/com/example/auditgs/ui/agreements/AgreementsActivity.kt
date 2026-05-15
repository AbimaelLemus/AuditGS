package com.example.auditgs.ui.agreements

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.auditgs.R
import com.example.auditgs.databinding.ActivityAgreementsBinding
import com.example.auditgs.databinding.ActivitySplashBinding
import com.example.auditgs.ui.splash.SplashActivity

class AgreementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgreementsBinding
    private val TAG = AgreementsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgreementsLogout.setOnClickListener {

        }
    }
}