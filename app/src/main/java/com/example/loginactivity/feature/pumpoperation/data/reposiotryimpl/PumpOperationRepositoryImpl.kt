package com.example.loginactivity.feature.pumpoperation.data.reposiotryimpl

import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.data.retrofit.PumpApiService
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository
import com.example.loginactivity.feature.pumpoperation.data.model.save.SaveTransactionDao
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionEntity
import com.google.gson.Gson
import javax.inject.Inject

class PumpOperationRepositoryImpl @Inject constructor(
    private val gson: Gson, private val pumpApiService: PumpApiService,
    private val apiService: LoginApiService,
    private val saveTransactionDao: SaveTransactionDao
) : PumpOperationRepository, BaseRepository(gson) {
    override suspend fun operatePump(cmd: String, params: String): Resource<PumpResponse> {

        return safeApiCall<PumpResponse>(
            apiCall = {
                pumpApiService.operatePump(cmd, params)
            },
            successType = PumpResponse::class.java,
            handleSuccess = {},
            handleFailure = {

            }
        )
    }

    override suspend fun saveTransactionRemote(request: TransactionDto): TransactionSaveResult {
        var apiResult: Resource<Any> = Resource.Failure("API call not executed")
        var localDbResult = false

        safeApiCall(
            apiCall = {
                apiService.saveInYardTransactions(request)
            },
            successType = GenericBaseResponse::class.java,
            handleSuccess = {
                apiResult = Resource.Success(Unit)
            },
            handleFailure = {
                apiResult = Resource.Failure(it)
            }
        ).let { result ->

            when (result) {
                is Resource.Success -> {

                    try {
                        saveTransactionDao.insertTransaction(
                            request.let {
                                TransactionEntity().apply {
                                    inyardSiteId = it.inyardSiteId
                                    fuelCode = it.fuelCode
                                    cardNumber = it.cardNumber
                                    quantity = it.quantity
                                    vinNumber = it.vinNumber
                                    isSyncedToRemote = true
                                }
                            }
                        )
                        localDbResult = true

                    } catch (e: Exception) {
                        return TransactionSaveResult.ApiSuccessLocalFailure(
                            e.message.toString(),
                            request
                        )
                    }

                }

                is Resource.Failure -> {

                    try {
                        saveTransactionDao.insertTransaction(
                            request.let {
                                TransactionEntity().apply {
                                    inyardSiteId = it.inyardSiteId
                                    fuelCode = it.fuelCode
                                    cardNumber = it.cardNumber
                                    quantity = it.quantity
                                    vinNumber = it.vinNumber
                                    isSyncedToRemote = false
                                }
                            }
                        )
                        localDbResult = true
                    } catch (e: Exception) {
                        return TransactionSaveResult.BothFailed(
                            result.message,
                            e.message.toString()
                        )
                    }

                }

                Resource.Loading -> {}

                else -> {}
            }

            return when {
                apiResult is Resource.Success && localDbResult -> TransactionSaveResult.Success(
                    request
                )

                apiResult is Resource.Failure && localDbResult -> TransactionSaveResult.ApiFailureLocalSuccess(
                    (apiResult as? Resource.Failure)?.message ?: "Unknown API error",
                    request
                )

                else -> TransactionSaveResult.BothFailed(
                    (apiResult as? Resource.Failure)?.message ?: "Unknown API error",
                    "Unknown local DB error"
                )
            }


        }

    }

    override suspend fun saveTransactionLocally(transactionEntity: TransactionEntity): Long {

        TODO("Not yet implemented")
    }
}