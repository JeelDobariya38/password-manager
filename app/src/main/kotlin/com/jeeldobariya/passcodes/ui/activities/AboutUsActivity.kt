package com.jeeldobariya.passcodes.ui.activities

import com.jeeldobariya.passcodes.databinding.ActivityAboutUsBinding
import com.jeeldobariya.passcodes.utils.Constant

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.WindowCompat

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up button click listeners
        setupButtonListeners()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(browserIntent)
    }

    private fun setupButtonListeners() {
        binding.cardSecurityGuidelines.setOnClickListener {
            openBrowser(Constant.SECURITY_GUIDE_URL)
        }

        binding.cardChangeLog.setOnClickListener {
            openBrowser(Constant.CHANGELOG_URL)
        }

        binding.cardLicense.setOnClickListener {
            openBrowser(Constant.LICENSE_URL)
        }

        binding.cardReportBug.setOnClickListener {
            openBrowser(Constant.REPORT_BUG_URL)
        }

        binding.cardWebsite.setOnClickListener {
            openBrowser(Constant.WEBSITE_URL)
        }

        binding.cardThirdPartyLicense.setOnClickListener {
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }
    }
}