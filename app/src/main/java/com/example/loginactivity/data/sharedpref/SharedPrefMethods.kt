package com.example.bvddriverfleetapp.data.sharedpref

interface SharedPrefMethods {

    fun saveAuthToken(token:String)
    fun getAuthToken() : String?
    fun setLoggedIn(isLoggedIn: Boolean)
    fun getLoggedIn() : Boolean
}