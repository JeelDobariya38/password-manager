package com.jeeldobariya.passcodes.ui

import android.content.Context
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

    companion object {
        const val THEME_PREFS_NAME = "theme_prefs"
        const val THEME_KEY = "selected_theme"
    }
    

    // List of available themes to cycle through
    private val THEMES = listOf(
        R.style.PasscodesTheme_Default,
        R.style.PasscodesTheme_Trusted,
        R.style.PasscodesTheme_Pink,
        R.style.PasscodesTheme_Cute
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPrefs = getSharedPreferences(SettingsActivity.THEME_PREFS_NAME, Context.MODE_PRIVATE)
        val savedThemeStyle = sharedPrefs.getInt(SettingsActivity.THEME_KEY, R.style.PasscodesTheme_Default)
        setTheme(savedThemeStyle)

        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitialLangSelection()

        // Add event onclick listener
        addOnClickListenerOnButton()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setInitialLangSelection() {
        val currentAppLocales: String = AppCompatDelegate.getApplicationLocales().toLanguageTags()

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

        binding.toggleThemeBtn.setOnClickListener {
            val sharedPrefs = getSharedPreferences(SettingsActivity.THEME_PREFS_NAME, Context.MODE_PRIVATE)
            val currentThemeStyle = sharedPrefs.getInt(SettingsActivity.THEME_KEY, R.style.PasscodesTheme_Default)

            val currentIndex = THEMES.indexOf(currentThemeStyle)
            val nextIndex = (currentIndex + 1) % THEMES.size
            val newThemeStyle = THEMES[nextIndex]

            // Save the new theme and restart the application to apply it
            sharedPrefs.edit().putInt(THEME_KEY, newThemeStyle).apply()
            recreate()
        }
    }
}
