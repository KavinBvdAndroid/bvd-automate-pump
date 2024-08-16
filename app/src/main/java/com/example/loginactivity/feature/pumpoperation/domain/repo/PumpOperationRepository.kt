package com.example.loginactivity.feature.pumpoperation.domain.repo

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionEntity

interface PumpOperationRepository {
    suspend fun operatePump(cmd: String, params: String): Resource<PumpResponse>
    suspend fun saveTransactionRemote(request: SaveTransactionDto) : TransactionSaveResult
    suspend fun saveTransactionLocally(transactionEntity: SaveTransactionEntity): Long

}