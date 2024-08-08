package com.example.bvddriverfleetapp.data.sharedpref

import android.content.SharedPreferences
import com.example.bvddriverfleetapp.core.utils.Constants

class SessionManager(private val sharedPreferences: SharedPreferences) : SharedPrefMethods {
    override fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(Constants.KEY_AUTH_TOKEN, token).apply()

    }

    override fun getAuthToken(): String? {
        return sharedPreferences.getString(Constants.KEY_AUTH_TOKEN, null)
    }

    override fun setLoggedIn(isLoggedIn: Boolean) {
        return sharedPreferences.edit().putBoolean(Constants.IS_LOGGED_IN, isLoggedIn).apply()
    }

    override fun getLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(Constants.IS_LOGGED_IN, false)
    }
}