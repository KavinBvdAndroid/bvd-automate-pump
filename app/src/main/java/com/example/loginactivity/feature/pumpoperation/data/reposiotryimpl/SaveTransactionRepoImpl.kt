package com.example.loginactivity.feature.pumpoperation.data.reposiotryimpl

import android.util.Log
import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.google.gson.Gson
import javax.inject.Inject

class SaveTransactionRepoImpl @Inject constructor(private val gson: Gson, private val apiService: LoginApiService): BaseRepository(gson) {

    suspend fun saveTransactionDetails(request: TransactionDto): Resource<GenericBaseResponse>{
        return safeApiCall(
            apiCall = {
                apiService.saveInYardTransactions(request)
            },
            successType = GenericBaseResponse::class.java,
            handleSuccess = {
                Log.d("Api call Response Success : ","${it}")
            },
            handleFailure = {

            }
        )
    }
}