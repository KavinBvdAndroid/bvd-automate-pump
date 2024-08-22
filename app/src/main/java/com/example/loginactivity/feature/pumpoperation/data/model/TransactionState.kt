package com.example.loginactivity.feature.pumpoperation.data.model

import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto

sealed class TransactionState {
    object Idle : TransactionState()
    object Loading : TransactionState()
    data class Success(val message: String, val saveTransactionDto: TransactionDto?=null) : TransactionState()
    data class PartialSuccess(val message: String,val saveTransactionDto: TransactionDto?=null) : TransactionState()
    data class Error(val message: String) : TransactionState()
}