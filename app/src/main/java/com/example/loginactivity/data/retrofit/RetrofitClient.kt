package com.example.loginactivity.data.retrofit

import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(private val sessionManager: SessionManager) {

    val client by lazy {

        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).addInterceptor(NetworkInterceptors(sessionManager))
            .build()
    }

    val pumpRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.PUMP_BASE_URL)
            .client(client)
            .build()

    }

    val pro1Retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()

    }

    val pro1RetrofitLocal: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL_LOCAL)
            .client(client)
            .build()

    }


    val pumpOperationService by lazy {
        pumpRetrofit.create(PumpApiService::class.java)
    }

    val loginApiService by lazy {
        pro1Retrofit.create(LoginApiService::class.java)
    }

    val vinNumberApiService by lazy {
        pro1Retrofit.create(VinNumberApiService::class.java)
    }


    val loginApiServiceLocal by lazy {
        pro1RetrofitLocal.create(LoginApiService::class.java)
    }

    val vinNumberApiServiceLocal by lazy {
        pro1RetrofitLocal.create(VinNumberApiService::class.java)
    }
}



