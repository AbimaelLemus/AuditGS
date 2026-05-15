package com.example.auditgs.ui.agreements

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.auditgs.databinding.ActivityAgreementsBinding
import com.example.auditgs.ui.login.LoginActivity

class AgreementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgreementsBinding
    private val TAG = AgreementsActivity::class.java.simpleName
    private lateinit var viewModel: AgreementsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgreementsLogout.setOnClickListener {
            viewModel.logout {

                val intent = Intent(this, LoginActivity::class.java)

                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                finish()
            }
        }
    }
}