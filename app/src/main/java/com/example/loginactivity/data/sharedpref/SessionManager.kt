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
        sharedPreferences.edit().putString(Constants.DRIVER_ID, driverId).apply()
    }

    override fun getDriverId(): String? {
        return sharedPreferences.getString(Constants.DRIVER_ID,null)
    }


    override fun saveTruckId(driverId: String) {
        sharedPreferences.edit().putInt(Constants.TRUCK_ID, 0).apply()
    }

    override fun getTruckId(): String? {
        return sharedPreferences.getString(Constants.TRUCK_ID, null)
    }



     fun setSwitchStateEnable(switchEnable: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.SWITCH_STATE, switchEnable).apply()
    }

     fun getSwitchStateEnable(): Boolean {
        return sharedPreferences.getBoolean(Constants.SWITCH_STATE, false)
    }

    override fun clearSharedPref(): Boolean {
        sharedPreferences.edit().clear().apply()
        setIsBoardingCompleted(true)
        return true
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun saveDouble(key: String, value: Double) {
        sharedPreferences.edit().putLong(key, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun saveTransactionDto(userJson: String) {
        sharedPreferences.edit().putString("transaction_dto",userJson).apply()
    }
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        return java.lang.Double.longBitsToDouble(
            sharedPreferences.getLong(key, java.lang.Double.doubleToRawLongBits(defaultValue))
        )
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

}