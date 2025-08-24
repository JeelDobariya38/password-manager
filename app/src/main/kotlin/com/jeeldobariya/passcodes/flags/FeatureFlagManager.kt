package com.jeeldobariya.passcodes.flags

import android.content.Context
import androidx.core.content.edit

class FeatureFlagManager private constructor(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var latestFeaturesEnabled: Boolean
        get() = prefs.getBoolean(KEY_LATEST_FEATURES, false)
        set(value) { prefs.edit { putBoolean(KEY_LATEST_FEATURES, value) } }

    companion object {
        private const val PREFS_NAME = "feature_flags"
        private const val KEY_LATEST_FEATURES = "latest_features_enabled"

        @Volatile private var INSTANCE: FeatureFlagManager? = null

        fun get(context: Context): FeatureFlagManager {
            return INSTANCE ?: synchronized(this) {
                val instance = FeatureFlagManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}
