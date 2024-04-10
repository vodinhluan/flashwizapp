package com.example.flashwiz_fe.data


//object UserPreferences {
//    private const val USER_PREFERENCES_NAME = "user_preferences"
//    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)
//
//    // Hàm để lưu thông tin người dùng vào DataStore
//    suspend fun saveUserToken(context: Context, tokenResponse: TokenResponse) {
//        context.dataStore.edit { preferences ->
//            preferences[PreferencesKeys.EMAIL] = tokenResponse.email
//            preferences[PreferencesKeys.ACCESS_TOKEN] = tokenResponse.accessToken
//        }
//    }
//
//    // Flow để lấy thông tin người dùng từ DataStore
//    val Context.userPreferences: Flow<TokenResponse>
//        get() = dataStore.data.map { preferences ->
//            TokenResponse(
//                preferences[PreferencesKeys.ACCESS_TOKEN] ?: "",
//                preferences[PreferencesKeys.EMAIL] ?: ""
//            )
//        }
//
//    // Phương thức để lưu trạng thái ghi nhớ đăng nhập vào DataStore
//    suspend fun saveRememberMePreference(context: Context, rememberMe: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[PreferencesKeys.REMEMBER_ME] = rememberMe
//        }
//    }
//
//    // Object để định nghĩa các khóa cho Preferences
//    private object PreferencesKeys {
//        val EMAIL = stringPreferencesKey("email")
//        val ACCESS_TOKEN = stringPreferencesKey("access_token")
////        val REMEMBER_ME = booleanPreferencesKey("remember_me")
//    }
//}





