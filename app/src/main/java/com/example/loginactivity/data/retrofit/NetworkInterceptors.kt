package com.example.loginactivity.data.retrofit

import okhttp3.Interceptor

object NetworkInterceptors {

    val headerInterceptor by lazy {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        }
    }
}