package com.example.loginactivity.feature.transaction.data.datasource

import com.example.loginactivity.feature.pumpoperation.data.model.save.SaveTransactionDao
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionEntity

class LocalTransactionDataSource(private val localDao: SaveTransactionDao) {
    suspend fun getAllTransactions(truckId:Int, driverId:Int): List<TransactionEntity> {
        return localDao.getAllTransactions(truckId,driverId)
    }

    suspend fun saveAllTransactions(transactionList:List<TransactionDto>){

    }

    suspend fun saveTransaction(transactionEntity: TransactionEntity){

    }
}