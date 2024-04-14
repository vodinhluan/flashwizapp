package com.example.flashwiz_fe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    private val _darkThemeEnabled = MutableLiveData(false)
    val darkThemeEnabled: LiveData<Boolean> = _darkThemeEnabled

    fun toggleTheme() {
        _darkThemeEnabled.value = !(_darkThemeEnabled.value ?: false)
    }
}