package com.jeeldobariya.passcodes.ui

import android.os.Bundle
import android.widget.Toast
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import android.view.View
import androidx.core.view.WindowCompat
import android.view.LayoutInflater

import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitialSelections()

        // Add event onclick listener
        addOnClickListenerOnButton()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setInitialSelections() {
        val currentAppLocales: String = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        Toast.makeText(this@SettingsActivity, currentAppLocales, Toast.LENGTH_SHORT).show()

        val languageTags = resources.getStringArray(R.array.lang_locale_tags)
        for ((index, localeTag) in languageTags.withIndex()) {
            if (currentAppLocales.contains(localeTag)) {
                binding.langSwitchDropdown.setSelection(index)
                return
            }
        }

        binding.langSwitchDropdown.setSelection(0)
        Toast.makeText(this@SettingsActivity, getString(R.string.something_went_wrong_msg), Toast.LENGTH_SHORT).show()
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton() {
        binding.langSwitchDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val languageTags = resources.getStringArray(R.array.lang_locale_tags)
                val localeTag = languageTags[position]
                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(localeTag)
                AppCompatDelegate.setApplicationLocales(appLocale)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Not needed in this case, as we've already set a default
            }
        }
    }
}
