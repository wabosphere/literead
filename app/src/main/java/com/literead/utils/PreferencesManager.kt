package com.literead.utils

import android.content.Context
import android.content.SharedPreferences
import com.literead.data.model.UserPreferences
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

/**
 * Gestionnaire des préférences utilisateur
 */
class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("literead_prefs", Context.MODE_PRIVATE)

    fun savePreferences(preferences: UserPreferences) {
        val json = Json.encodeToString(preferences)
        sharedPreferences.edit().putString(KEY_PREFERENCES, json).apply()
    }

    fun getPreferences(): UserPreferences {
        val json = sharedPreferences.getString(KEY_PREFERENCES, null)
        return if (json != null) {
            try {
                Json.decodeFromString<UserPreferences>(json)
            } catch (e: Exception) {
                UserPreferences()
            }
        } else {
            UserPreferences()
        }
    }

    fun setTheme(theme: String) {
        val current = getPreferences()
        savePreferences(current.copy(theme = theme))
    }

    fun setFontSize(size: Int) {
        val current = getPreferences()
        savePreferences(current.copy(fontSize = size))
    }

    fun setReadingMode(mode: String) {
        val current = getPreferences()
        savePreferences(current.copy(readingMode = mode))
    }

    fun setBrightness(brightness: Float) {
        val current = getPreferences()
        savePreferences(current.copy(brightness = brightness))
    }

    companion object {
        private const val KEY_PREFERENCES = "user_preferences"
    }
}
