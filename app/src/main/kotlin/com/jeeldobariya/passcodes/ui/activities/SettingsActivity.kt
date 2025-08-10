package com.jeeldobariya.passcodes.ui.activities

import com.jeeldobariya.passcodes.databinding.ActivitySettingsBinding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

//import androidx.core.view.WindowCompat

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    companion object {
        const val LANG_PREFS_NAME = "language_prefs"
        const val THEME_PREFS_NAME = "theme_prefs"
        const val THEME_KEY = "selected_theme"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up button click listeners
        setupButtonListeners()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setupButtonListeners() {
        binding.aboutUsButton.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
    }
}
