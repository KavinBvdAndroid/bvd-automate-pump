package com.example.loginactivity.feature.auth.data.model

import android.util.Log
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.feature.auth.domain.LoginRepository
import com.google.gson.Gson
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val apiService: LoginApiService,
    private val sessionManager: SessionManager
) :
    LoginRepository, BaseRepository(gson) {

    override suspend fun loginUser(email: String, password: String): Resource<LoginResponse> {

        return safeApiCall<LoginResponse>(
            apiCall = {
                apiService.loginUser(email, password)
            },
            successType = LoginResponse::class.java,
            handleSuccess = { it ->
                Log.d("auth token", "" + it.toString())
                if (it.has("access_token")) {
                    saveAuthToken(it.get("access_token").asString)
                    saveLoginState(true)
                }
                if (it.has("user_id")) {
                    saveDriverId(it.get("user_id").asString)
                }
            },
            handleFailure = {
                saveDriverId(null.toString())
            }
        )

    }

    override fun saveAuthToken(accessToken: String) {
        sessionManager.saveAuthToken(accessToken)
    }

    override fun saveLoginState(isLoggedIn: Boolean) {
        sessionManager.setLoggedIn(isLoggedIn)
    }

    override fun getLoginState(): Boolean {
        return sessionManager.getLoggedIn()
    }

    override fun saveDriverId(asString: String) {
        sessionManager.saveDriverId(asString)
    }

    override fun getDriverId(): String? {
       return sessionManager.getDriverId()
    }

    override fun logOut() {
        sessionManager.clearSharedPref()
        saveLoginState(false)
    }
}
