package com.example.loginactivity.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.loginactivity.data.retrofit.PumpApiService
import com.example.loginactivity.data.retrofit.RetrofitClient
import com.example.loginactivity.feature.automatefuel.data.reposiotryimpl.PumpOperationRepositoryImpl
import com.example.loginactivity.feature.automatefuel.domain.usecase.PumpOperationRepository

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkDatabaseModule {

    @Provides
    fun getApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun getRetrofitApiService(): PumpApiService {
        return RetrofitClient.pumpOperationService
    }

//    @Provides
//    fun getLocalDataBaseInstance(context: Context): AppDataBase {
//        return AppDataBase.getDbInstance(context)
//    }
//
//    @Provides
//    fun provideSharedPref(application: Application): SharedPreferences {
//        return application.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
//    }
//
//    @Provides
//    fun provideSessionManager(sharedPreferences: SharedPreferences): SessionManager {
//        return SessionManager(sharedPreferences)
//    }


    @Provides
    fun providePumpOperationRepository(gson: Gson, pumpApiService: PumpApiService): PumpOperationRepository {
        return PumpOperationRepositoryImpl(gson, pumpApiService)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}