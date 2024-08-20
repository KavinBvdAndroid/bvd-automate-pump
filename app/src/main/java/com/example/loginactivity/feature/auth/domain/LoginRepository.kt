package com.example.loginactivity.feature.auth.domain

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.auth.data.model.LoginResponse

interface LoginRepository {
    suspend fun loginUser(email: String, password: String): Resource<LoginResponse>
    fun saveAuthToken(accessToken: String)
    fun saveLoginState(isLoggedIn: Boolean)
    fun getLoginState(): Boolean
    fun saveDriverId(asString: String)
    fun getDriverId():String
    fun logOut()
}