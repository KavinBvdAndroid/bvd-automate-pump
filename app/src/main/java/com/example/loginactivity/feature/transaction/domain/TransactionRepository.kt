package com.example.loginactivity.feature.transaction.domain

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto

interface TransactionRepository {

    suspend fun getAllTransactions(truckId:Int, driverId:Int) : Resource<List<TransactionDto>>
    suspend fun saveAllTransactions(transactionDto: List<TransactionDto>)
    suspend fun saveTransaction(transactionDto: TransactionDto): TransactionSaveResult
}