package com.example.loginactivity.data.retrofit

import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptors @Inject constructor(private val sessionManager: SessionManager) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = sessionManager.getAuthToken()
        val requestBuilder = chain.request().newBuilder()
            .header("Content-Type", "application/json")

        if (authToken != null) {
            requestBuilder.header("Authorization", "Bearer $authToken")
        }

        return chain.proceed(requestBuilder.build())    }
}