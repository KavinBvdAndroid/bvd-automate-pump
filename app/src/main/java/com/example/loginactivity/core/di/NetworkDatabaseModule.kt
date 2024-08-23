package com.example.loginactivity.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.bvddriverfleetapp.data.sharedpref.SharedPrefMethods
import com.example.loginactivity.core.base.utils.Constants
import com.example.loginactivity.data.local.AppDataBase
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.data.retrofit.NetworkInterceptors
import com.example.loginactivity.data.retrofit.PumpApiService
import com.example.loginactivity.data.retrofit.RetrofitClient
import com.example.loginactivity.data.retrofit.VinNumberApiService
import com.example.loginactivity.feature.auth.data.model.LoginRepositoryImpl
import com.example.loginactivity.feature.auth.domain.LoginRepository
import com.example.loginactivity.feature.maps.data.repoimpl.FetchInYardSitesRepositoryImpl
import com.example.loginactivity.feature.maps.domain.FetchInYardSitesRepository
import com.example.loginactivity.feature.pumpoperation.data.model.save.SaveTransactionDao
import com.example.loginactivity.feature.pumpoperation.data.reposiotryimpl.PumpOperationRepositoryImpl
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository
import com.example.loginactivity.feature.pumpoperation.domain.usecase.StartPumpUseCase
import com.example.loginactivity.feature.pumpoperation.domain.usecase.StopPumpUseCase
import com.example.loginactivity.feature.transaction.data.TransactionRepositoryImpl
import com.example.loginactivity.feature.transaction.data.datasource.LocalTransactionDataSource
import com.example.loginactivity.feature.transaction.data.datasource.RemoteTransactionDataSource
import com.example.loginactivity.feature.transaction.domain.TransactionRepository
import com.example.loginactivity.feature.transaction.domain.usecases.GetSavedTransactionUseCase
import com.example.loginactivity.feature.vinnumber.VinNumberRepository
import com.example.loginactivity.feature.vinnumber.VinNumberRepositoryImpl
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
    fun provideSessionManager(sharedPreferences: SharedPreferences): SessionManager {
        return SessionManager(sharedPreferences)
    }

    @Provides
    fun provideAuthInterceptor(sharedPref: SharedPrefMethods): NetworkInterceptors {
        return NetworkInterceptors(sharedPref)
    }

    @Provides
    fun getApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun getRetrofitApiService(sessionManager: SessionManager): PumpApiService {
        return RetrofitClient(sessionManager).pumpOperationService
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
    fun providePumpOperationRepository(
        gson: Gson,
        pumpApiService: PumpApiService,
        apiService: LoginApiService,
        saveTransactionDao: SaveTransactionDao
    ): PumpOperationRepository {
        return PumpOperationRepositoryImpl(gson, pumpApiService, apiService, saveTransactionDao)
    }

    @Provides
    fun provideStartUseCase(pumpOperationRepository: PumpOperationRepository): StartPumpUseCase {
        return StartPumpUseCase((pumpOperationRepository))
    }

    @Provides
    fun provideStopUseCase(pumpOperationRepository: PumpOperationRepository): StopPumpUseCase {
        return StopPumpUseCase((pumpOperationRepository))
    }

//    @Provides
//    fun provideSaveTransactionUseCase(transactionRepository: TransactionRepository): SaveTransactionUseCase {
//        return SaveTransactionUseCase((transactionRepository))
//    }

    @Provides
    fun provideGetSavedTransactionUSeCase(transactionRepository: TransactionRepository): GetSavedTransactionUseCase {
        return GetSavedTransactionUseCase((transactionRepository))
    }

    @Provides
    fun providesLoginApiService(sessionManager: SessionManager): LoginApiService {
        return RetrofitClient(sessionManager).loginApiServiceLocal
    }

    @Provides
    fun providesVinNumberApiService(sessionManager: SessionManager): VinNumberApiService {
        return RetrofitClient(sessionManager).vinNumberApiServiceLocal
    }
//    @Provides
//    fun ProvidesVinNumberApiServiceLocal(sessionManager: SessionManager): VinNumberApiService {
//        return RetrofitClient(sessionManager).vinNumberApiServiceLocal
//    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideTransactionDao(database: AppDataBase): SaveTransactionDao {
        return database.transactionDao()
    }


    //Repository Impl
    @Provides
    fun providesLoginRepository(
        gson: Gson,
        loginApiService: LoginApiService,
        sessionManager: SessionManager
    ): LoginRepository {
        return LoginRepositoryImpl(gson, loginApiService, sessionManager)
    }

    @Provides
    fun providesVinNumberRepository(
        gson: Gson,
        vinNumberApiService: LoginApiService,
        sessionManager: SessionManager
    ): VinNumberRepository {
        return VinNumberRepositoryImpl(gson, vinNumberApiService,sessionManager)
    }

    @Provides
    fun providesFetchInYardSitesRepository(
        gson: Gson,
        loginApiService: LoginApiService
    ): FetchInYardSitesRepository {
        return FetchInYardSitesRepositoryImpl(gson, loginApiService)
    }

    @Provides
    fun providesTransactionRepository(
        gson: Gson,
        localTransactionDataSource: LocalTransactionDataSource,
        remoteTransactionDataSource: RemoteTransactionDataSource
    ): TransactionRepository {
        return TransactionRepositoryImpl(
            localTransactionDataSource,
            remoteTransactionDataSource,
            gson
        )
    }
    @Provides
    fun provideLocalTransactionDataSource(apiService: SaveTransactionDao):LocalTransactionDataSource{
        return LocalTransactionDataSource(apiService)
    }
    @Provides
    fun provideRemoteTransactionDataSource(apiService: LoginApiService):RemoteTransactionDataSource{
        return RemoteTransactionDataSource(apiService)
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideSharedPref(application: Application): SharedPreferences {
        return application.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }


}