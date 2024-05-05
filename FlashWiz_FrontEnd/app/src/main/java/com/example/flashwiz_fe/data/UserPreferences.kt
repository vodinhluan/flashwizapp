package com.example.flashwiz_fe.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val myPreferencesDataStore = context.dataStore

    private object PreferencesKey {
        val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
        val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val KEY_USER_ID = stringPreferencesKey("user_id")
        val KEY_USER_NAME = stringPreferencesKey("user_name")
    }

    suspend fun saveUserToken(token: String) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKey.KEY_ACCESS_TOKEN] = token
        }
    }

    suspend fun saveUserEmail(email: String) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKey.KEY_USER_EMAIL] = email
        }
    }
    suspend fun saveUserId(userId: Int) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKey.KEY_USER_ID] = userId.toString()
        }
    }
    suspend fun saveUserName(userName: String) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKey.KEY_USER_NAME] = userName
        }
    }


    suspend fun saveIsLoggedIn(isLoggedIn: Boolean) {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKey.KEY_IS_LOGGED_IN] = isLoggedIn
        }
    }

    // Helper functions to retrieve saved data (consider implementing)
    suspend fun getUserToken(): String? {
        val preferences = myPreferencesDataStore.data.first()
        return preferences[PreferencesKey.KEY_ACCESS_TOKEN]
    }

    suspend fun getUserEmail(): String? {
        val preferences = myPreferencesDataStore.data.first()
        return preferences[PreferencesKey.KEY_USER_EMAIL]
    }

    suspend fun getIsLoggedIn(): Boolean {
        val preferences = myPreferencesDataStore.data.first()
        return preferences[PreferencesKey.KEY_IS_LOGGED_IN] ?: false
    }
    suspend fun getUserId(): String? {
        val preferences = myPreferencesDataStore.data.first()
        return preferences[PreferencesKey.KEY_USER_ID]
    }
    suspend fun getUserName(): String? {
        val preferences = myPreferencesDataStore.data.first()
        return preferences[PreferencesKey.KEY_USER_NAME]
    }
    suspend fun clearData() {
        myPreferencesDataStore.edit { preferences ->
            preferences.clear()
        }
    }


}