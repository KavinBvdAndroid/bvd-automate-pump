package com.example.loginactivity.data.retrofit

import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.bvddriverfleetapp.data.sharedpref.SharedPrefMethods
import com.example.loginactivity.core.base.testdatas.mockToken
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptors @Inject constructor(private val sessionManager: SharedPrefMethods) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = sessionManager.getAuthToken()
        val requestBuilder = chain.request().newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")


        if (authToken != null) {
            requestBuilder.header("Authorization", "Bearer $mockToken")
        }

//        requestBuilder.header("Authorization", "Bearer $sessionManager.")

        return chain.proceed(requestBuilder.build())
    }
}