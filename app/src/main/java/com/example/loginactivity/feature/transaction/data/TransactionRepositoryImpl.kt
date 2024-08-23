package com.example.loginactivity.feature.transaction.data

import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.data.datasource.LocalTransactionDataSource
import com.example.loginactivity.feature.transaction.data.datasource.RemoteTransactionDataSource
import com.example.loginactivity.feature.transaction.domain.TransactionRepository
import com.google.gson.Gson
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataSource: LocalTransactionDataSource,
    private val remoteDataSource: RemoteTransactionDataSource,
    private val gson: Gson
) : TransactionRepository, BaseRepository(gson) {
    override suspend fun getAllTransactions(

        driverId: Int
    ): Resource<List<TransactionDto>> {

//        return safeApiCallList(
//            apiCall = {
//                remoteDataSource.getAllTransactions(truckId, driverId)
//            },
//            successType = TransactionDto::class.java,
//            handleSuccess = {
//
//            },
//            handleFailure = {
//            }
//        )

        return try {
            val entities = localDataSource.getAllTransactions(driverId)
            val dto = entities.map { it.toDto() }
            Resource.Success(dto)
        }catch (e:Exception){
            Resource.Failure("Failed to fetch transactions from local database: ${e.message}")
        }

    }

    override suspend fun saveTransaction(transactionDto: TransactionDto): TransactionSaveResult {
        var apiResult: Resource<Any> = Resource.Failure("API call not executed")
        var localDbResult = false

        safeApiCall(
            apiCall = {
                remoteDataSource.saveTransaction(transactionDto)
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
                        val transactionEntity = transactionDto.toEntity(isSyncedToRemote = true)
                        localDataSource.saveTransaction(transactionEntity)
                        localDbResult = true

                    } catch (e: Exception) {
                        return TransactionSaveResult.ApiSuccessLocalFailure(
                            e.message.toString(),
                            transactionDto
                        )
                    }

                }

                is Resource.Failure -> {

                    try {
                        val transactionEntity = transactionDto.toEntity(isSyncedToRemote = true)
                        localDataSource.saveTransaction(transactionEntity)
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
                    transactionDto
                )

                apiResult is Resource.Failure && localDbResult -> TransactionSaveResult.ApiFailureLocalSuccess(
                    (apiResult as? Resource.Failure)?.message ?: "Unknown API error",
                    transactionDto
                )

                else -> TransactionSaveResult.BothFailed(
                    (apiResult as? Resource.Failure)?.message ?: "Unknown API error",
                    "Unknown local DB error"
                )
            }


        }

    }

    override suspend fun saveAllTransactions(transactionDto: List<TransactionDto>) {
        TODO("Not yet implemented")
    }


}