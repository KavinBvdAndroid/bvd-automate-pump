package com.example.loginactivity.feature.transaction.data.datasource

import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import okhttp3.ResponseBody
import retrofit2.Response

class RemoteTransactionDataSource(private val apiService: LoginApiService) {
    suspend fun getAllTransactions(truckId: Int, driverId: Int) {

    }

    suspend fun saveAllTransactions(transactionList: List<TransactionDto>) {

    }

    suspend fun saveTransaction(transactionDto: TransactionDto): Response<ResponseBody> {
        return apiService.saveInYardTransactions(transactionDto)
    }
}