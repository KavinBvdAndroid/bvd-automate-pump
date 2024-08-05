package com.example.loginactivity.feature.automatefuel.data.reposiotryimpl

import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.PumpApiService
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse
import com.example.loginactivity.feature.automatefuel.domain.usecase.PumpOperationRepository
import com.google.gson.Gson
import javax.inject.Inject

class PumpOperationRepositoryImpl @Inject constructor(private val gson : Gson, private val pumpApiService: PumpApiService): PumpOperationRepository, BaseRepository(gson)
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
}