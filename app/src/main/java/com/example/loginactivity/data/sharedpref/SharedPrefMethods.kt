package com.example.bvddriverfleetapp.data.sharedpref

import com.example.loginactivity.data.sharedpref.LoggingSharedPrefMethods

interface SharedPrefMethods : LoggingSharedPrefMethods {

    fun saveAuthToken(token:String)
    fun getAuthToken() : String?
    fun setLoggedIn(isLoggedIn: Boolean)
    fun getLoggedIn() : Boolean
    fun setIsBoardingCompleted(isBoardingShowed:Boolean)
    fun getIsBoardingCompleted():Boolean
    fun saveDriverId(driverId:String)
    fun getDriverId(): Int
    fun saveTruckId(driverId:String)
    fun getTruckId(): Int
}