package com.example.loginactivity.feature.transaction.data.datasource

import com.example.loginactivity.feature.pumpoperation.data.model.save.SaveTransactionDao
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionEntity

class LocalTransactionDataSource(private val localDao: SaveTransactionDao) {
    suspend fun getAllTransactions(driverId:Int): List<TransactionEntity> {
        return localDao.getAllTransactions(driverId)
    }

    suspend fun saveAllTransactions(transactionList:List<TransactionDto>){

    }

    suspend fun saveTransaction(transactionEntity: TransactionEntity){

    }
}