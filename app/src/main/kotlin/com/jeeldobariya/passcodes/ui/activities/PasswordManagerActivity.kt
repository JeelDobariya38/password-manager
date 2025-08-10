package com.jeeldobariya.passcodes.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.databinding.ActivityPasswordManagerBinding


class PasswordManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordManagerBinding // Use lateinit for binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add event onclick listener
        addOnClickListenerOnButton(binding)

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton(binding: ActivityPasswordManagerBinding) {
        binding.savePasswordBtn.setOnClickListener {
            val savePasswordIntent = Intent(this, SavePasswordActivity::class.java)
            startActivity(savePasswordIntent)
        }

        binding.loadPasswordBtn.setOnClickListener {
            val loadPasswordIntent = Intent(this, LoadPasswordActivity::class.java)
            startActivity(loadPasswordIntent)
        }

        binding.securityCheckBtn.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_future_feat_clause), Toast.LENGTH_SHORT).show()
        }

        binding.importPasswordBtn.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_future_feat_clause), Toast.LENGTH_SHORT).show()
        }

        binding.exportPasswordBtn.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_future_feat_clause), Toast.LENGTH_SHORT).show()
        }
    }
}