package com.example.loginactivity.feature.pumpoperation.data.reposiotryimpl

import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.data.retrofit.PumpApiService
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionEntity
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository
import com.google.gson.Gson
import javax.inject.Inject

class PumpOperationRepositoryImpl @Inject constructor(private val gson : Gson, private val pumpApiService: PumpApiService, private val apiService: LoginApiService): PumpOperationRepository, BaseRepository(gson)
{
    override suspend fun operatePump(cmd: String, params: String) : Resource<PumpResponse> {

        return safeApiCall<PumpResponse>(
            apiCall = {
                pumpApiService.operatePump(cmd,params)
            },
            successType = PumpResponse::class.java,
            handleSuccess = {}
        )
    }

    override suspend fun saveTransactionRemote(request: SaveTransactionDto): Resource<GenericBaseResponse> {
        return safeApiCall(
            apiCall = {
                apiService.saveInYardTransactions(request)
            },
            successType = GenericBaseResponse::class.java,
            handleSuccess = {

            }
        )

    }

    override suspend fun saveTransactionLocally(transactionEntity: SaveTransactionEntity): Long {

        TODO("Not yet implemented")
    }
}