package com.example.loginactivity.feature.maps.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.maps.domain.FetchInYardSitesRepository
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionEntity
import com.example.loginactivity.feature.transaction.domain.TransactionRepository
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(private val transactionRepository: TransactionRepository){

    suspend fun getAllTransactions(truckId:Int, driverId: Int): Resource<List<TransactionDto>> {
        return transactionRepository.getAllTransactions(truckId,driverId)
    }
}