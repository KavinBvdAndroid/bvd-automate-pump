package com.example.bvddriverfleetapp.data.sharedpref

import android.content.SharedPreferences
import com.example.loginactivity.core.base.utils.Constants
import javax.inject.Inject

class SessionManager @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SharedPrefMethods {
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

    override fun setIsBoardingCompleted(isBoardingShowed: Boolean) {
        return sharedPreferences.edit().putBoolean(Constants.IS_BOARDING_SHOWN, isBoardingShowed)
            .apply()
    }

    override fun getIsBoardingCompleted(): Boolean {
        return sharedPreferences.getBoolean(Constants.IS_BOARDING_SHOWN, false)
    }

    override fun saveDriverId(driverId: String) {
        sharedPreferences.edit().putInt(Constants.DRIVER_ID, 0).apply()
    }

    override fun getDriverId(): Int {
        return sharedPreferences.getInt(Constants.DRIVER_ID, 0)
    }

    override fun saveTruckId(driverId: String) {
        sharedPreferences.edit().putInt(Constants.TRUCK_ID, 0).apply()
    }

    override fun getTruckId(): Int {
        return sharedPreferences.getInt(Constants.TRUCK_ID, 0)
    }

    override fun clearSharedPref(): Boolean {
        sharedPreferences.edit().clear().apply()
        setIsBoardingCompleted(true)
        return true
    }
}