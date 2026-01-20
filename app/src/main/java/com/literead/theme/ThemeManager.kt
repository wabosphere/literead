package com.literead.theme

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.literead.R

/**
 * Gestionnaire des thèmes de l'application
 */
object ThemeManager {
    const val THEME_LIGHT = "light"
    const val THEME_DARK = "dark"
    const val THEME_SEPIA = "sepia"
    const val THEME_AMOLED = "amoled"

    fun applyTheme(context: Context, theme: String) {
        when (theme) {
            THEME_LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                if (context is Activity) {
                    context.setTheme(R.style.Theme_LiteRead)
                    context.recreate()
                }
            }
            THEME_DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                if (context is Activity) {
                    context.setTheme(R.style.Theme_LiteRead_Dark)
                    context.recreate()
                }
            }
            THEME_SEPIA -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                if (context is Activity) {
                    context.setTheme(R.style.Theme_LiteRead_Sepia)
                    context.recreate()
                }
            }
            THEME_AMOLED -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                if (context is Activity) {
                    context.setTheme(R.style.Theme_LiteRead_Amoled)
                    context.recreate()
                }
            }
        }
    }

    fun getThemeColors(theme: String, context: Context): ThemeColors {
        return when (theme) {
            THEME_LIGHT -> ThemeColors(
                primary = context.getColor(R.color.primary_light),
                background = context.getColor(R.color.background_light),
                surface = context.getColor(R.color.surface_light),
                textPrimary = context.getColor(R.color.text_primary_light),
                textSecondary = context.getColor(R.color.text_secondary_light)
            )
            THEME_DARK -> ThemeColors(
                primary = context.getColor(R.color.primary_dark),
                background = context.getColor(R.color.background_dark),
                surface = context.getColor(R.color.surface_dark),
                textPrimary = context.getColor(R.color.text_primary_dark),
                textSecondary = context.getColor(R.color.text_secondary_dark)
            )
            THEME_SEPIA -> ThemeColors(
                primary = context.getColor(R.color.primary_sepia),
                background = context.getColor(R.color.background_sepia),
                surface = context.getColor(R.color.surface_sepia),
                textPrimary = context.getColor(R.color.text_primary_sepia),
                textSecondary = context.getColor(R.color.text_secondary_sepia)
            )
            THEME_AMOLED -> ThemeColors(
                primary = context.getColor(R.color.primary_amoled),
                background = context.getColor(R.color.background_amoled),
                surface = context.getColor(R.color.surface_amoled),
                textPrimary = context.getColor(R.color.text_primary_amoled),
                textSecondary = context.getColor(R.color.text_secondary_amoled)
            )
            else -> ThemeColors()
        }
    }
}

data class ThemeColors(
    val primary: Int = 0,
    val background: Int = 0,
    val surface: Int = 0,
    val textPrimary: Int = 0,
    val textSecondary: Int = 0
)
