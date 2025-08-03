package com.jeeldobariya.passcodes.ui;

import android.content.Context
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.databinding.ActivityAboutUsBinding;
import com.jeeldobariya.passcodes.utils.Constant;

public class AboutUsActivity : AppCompatActivity() {

  private lateinit var binding: ActivityAboutUsBinding
  
  override fun onCreate(savedInstanceState: Bundle?) {
    val sharedPrefs = getSharedPreferences(SettingsActivity.THEME_PREFS_NAME, Context.MODE_PRIVATE)
    val savedThemeStyle = sharedPrefs.getInt(SettingsActivity.THEME_KEY, R.style.PasscodesTheme_Default)
    setTheme(savedThemeStyle)
    
    super.onCreate(savedInstanceState);
    binding = ActivityAboutUsBinding.inflate(layoutInflater);
    setContentView(binding.root);

    // Add event onclick listener
    addOnClickListenerOnButton();

    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(window, false);
  }

  private fun openBrowser(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
    startActivity(browserIntent);
  }

  // Added all the onclick event listiners
  private fun addOnClickListenerOnButton() {
    binding.viewSecurityGuidelinesBtn.setOnClickListener {
        openBrowser(Constant.SECURITY_GUIDE_URL);
    };

    binding.viewChangeLogBtn.setOnClickListener {
        openBrowser(Constant.CHANGELOG_URL);
    };

    binding.viewLicenseBtn.setOnClickListener {
        openBrowser(Constant.LICENSE_URL);
    };
  }
}
