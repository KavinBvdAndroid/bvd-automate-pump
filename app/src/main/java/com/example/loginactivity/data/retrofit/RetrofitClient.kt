package com.example.loginactivity.data.retrofit

import com.example.loginactivity.core.base.utils.Constants
import com.example.loginactivity.data.retrofit.NetworkInterceptors.headerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val client by lazy {

        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).addInterceptor(headerInterceptor)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.PUMP_BASE_URL)
            .client(client)
            .build()

    }

    val pumpOperationService by lazy {
        retrofit.create(PumpApiService::class.java)
    }
}



