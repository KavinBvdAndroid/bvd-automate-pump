package com.example.loginactivity.feature.pumpoperation.data.model

import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto

sealed class TransactionSaveResult {

    data class Success(val savedTransactionDto: SaveTransactionDto?=null) : TransactionSaveResult()
    data class ApiFailureLocalSuccess(val apiError: String, val SaveTransactionDto: SaveTransactionDto?=null) : TransactionSaveResult()
    data class ApiSuccessLocalFailure(val localError: String,val savedTransactionDto: SaveTransactionDto) : TransactionSaveResult()
    data class BothFailed(val apiError: String, val localError: String) : TransactionSaveResult()
}