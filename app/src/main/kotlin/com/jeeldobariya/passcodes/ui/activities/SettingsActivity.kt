package com.jeeldobariya.passcodes.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.databinding.ActivitySettingsBinding
import androidx.core.content.edit

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val prefs by lazy { getSharedPreferences("app_prefs", MODE_PRIVATE) }

    companion object {
        const val THEME_KEY = "selected_theme"
        const val PALETTE_KEY = "selected_palette"
        const val LANGUAGE_KEY = "selected_language"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupButtonListeners()
        updateUIWithCurrentSettings()

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setupButtonListeners() {
        binding.aboutUsButton.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
        binding.languageSwitchButton.setOnClickListener {
            showLanguageDialog()
        }
        binding.themeSwitchButton.setOnClickListener {
            showThemeDialog()
        }
        binding.paletteSwitchButton.setOnClickListener {
            showPaletteDialog()
        }
    }

    private fun updateUIWithCurrentSettings() {
        // Update Language Text
        val currentLangTag = prefs.getString(LANGUAGE_KEY, "en-US")
        val languageNames = resources.getStringArray(R.array.lang_locale_names)
        val languageTags = resources.getStringArray(R.array.lang_locale_tags)
        val currentLangIndex = languageTags.indexOf(currentLangTag)
        binding.currentLanguage.text = languageNames.getOrNull(currentLangIndex) ?: "English"

        // Update Theme Text
        val currentThemeValue = prefs.getString(THEME_KEY, "system")
        val themeNames = resources.getStringArray(R.array.theme_options)
        val themeValues = resources.getStringArray(R.array.theme_values)
        val currentThemeIndex = themeValues.indexOf(currentThemeValue)
        binding.currentTheme.text = themeNames.getOrNull(currentThemeIndex) ?: getString(R.string.theme_system)

        // Update Palette Text
        val currentPaletteValue = prefs.getString(PALETTE_KEY, "default")
        val paletteNames = resources.getStringArray(R.array.palette_options)
        val paletteValues = resources.getStringArray(R.array.palette_values)
        val currentPaletteIndex = paletteValues.indexOf(currentPaletteValue)
        binding.currentPalette.text = paletteNames.getOrNull(currentPaletteIndex) ?: getString(R.string.palette_default)
    }

    private fun showLanguageDialog() {
        val languageTags = resources.getStringArray(R.array.lang_locale_tags)
        val languageNames = resources.getStringArray(R.array.lang_locale_names)
        val currentLangTag = prefs.getString(LANGUAGE_KEY, "en-US")
        var selectedLanguageIndex = languageTags.indexOf(currentLangTag)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_language_title)
            .setSingleChoiceItems(languageNames, selectedLanguageIndex) { _, which ->
                selectedLanguageIndex = which
            }
            .setNegativeButton(R.string.text_cancel, null)
            .setPositiveButton(R.string.text_apply) { _, _ ->
                val newLangTag = languageTags[selectedLanguageIndex]
                if (newLangTag != currentLangTag) {
                    prefs.edit { putString(LANGUAGE_KEY, newLangTag) }
                    // Update Language
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(newLangTag)
                    )
                    // Update UI
                    updateUIWithCurrentSettings()
                }
            }
            .show()
    }

    private fun showThemeDialog() {
        val themeNames = resources.getStringArray(R.array.theme_options)
        val themeValues = resources.getStringArray(R.array.theme_values)
        val currentThemeValue = prefs.getString(THEME_KEY, "system")
        var selectedThemeIndex = themeValues.indexOf(currentThemeValue)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_theme_title)
            .setSingleChoiceItems(themeNames, selectedThemeIndex) { _, which ->
                selectedThemeIndex = which
            }
            .setNegativeButton(R.string.text_cancel, null)
            .setPositiveButton(R.string.text_apply) { _, _ ->
                val newThemeValue = themeValues[selectedThemeIndex]
                if (newThemeValue != currentThemeValue) {
                    prefs.edit { putString(THEME_KEY, newThemeValue) }
                    applyTheme(newThemeValue)
                }
            }
            .show()
    }

    private fun applyTheme(themeValue: String) {
        val themeMode = when (themeValue) {
            "light" -> AppCompatDelegate.MODE_NIGHT_NO
            "dark" -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(themeMode)
        recreate()
    }

    private fun showPaletteDialog() {
        val paletteNames = resources.getStringArray(R.array.palette_options)
        val paletteValues = resources.getStringArray(R.array.palette_values)
        val currentPaletteValue = prefs.getString(PALETTE_KEY, "default")
        var selectedPaletteIndex = paletteValues.indexOf(currentPaletteValue)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_palette_title)
            .setSingleChoiceItems(paletteNames, selectedPaletteIndex) { _, which ->
                selectedPaletteIndex = which
            }
            .setNegativeButton(R.string.text_cancel, null)
            .setPositiveButton(R.string.text_apply) { _, _ ->
                val newPaletteValue = paletteValues[selectedPaletteIndex]
                if (newPaletteValue != currentPaletteValue) {
                    prefs.edit { putString(PALETTE_KEY, newPaletteValue) }
                    applyPalette(newPaletteValue)
                }
            }
            .show()
    }

    // TODO: implement this method
    private fun applyPalette(paletteValue: String) {
        recreate()
    }
}