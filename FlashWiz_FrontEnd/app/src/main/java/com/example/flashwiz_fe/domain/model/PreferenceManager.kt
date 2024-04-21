package com.example.flashwiz_fe.domain.model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    var darkModeEnabled: Boolean
        get() = prefs.getBoolean("DARK_MODE_ENABLED", false)
        set(value) = prefs.edit().putBoolean("DARK_MODE_ENABLED", value).apply()
}

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val PreferenceManager = PreferenceManager(application)
    private val _darkThemeEnabled = MutableLiveData(PreferenceManager.darkModeEnabled)
    val darkThemeEnabled: LiveData<Boolean> = _darkThemeEnabled

    fun toggleTheme() {
        val newThemeState = !(_darkThemeEnabled.value ?: false)
        _darkThemeEnabled.value = newThemeState
        PreferenceManager.darkModeEnabled = newThemeState
    }
}