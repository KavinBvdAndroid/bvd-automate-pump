package com.example.loginactivity.feature.pumpoperation.data.model

import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto

sealed class TransactionSaveResult {

    data class Success(val savedTransactionDto: TransactionDto?=null) : TransactionSaveResult()
    data class ApiFailureLocalSuccess(val apiError: String, val SaveTransactionDto: TransactionDto?=null) : TransactionSaveResult()
    data class ApiSuccessLocalFailure(val localError: String,val savedTransactionDto: TransactionDto) : TransactionSaveResult()
    data class BothFailed(val apiError: String, val localError: String) : TransactionSaveResult()
}