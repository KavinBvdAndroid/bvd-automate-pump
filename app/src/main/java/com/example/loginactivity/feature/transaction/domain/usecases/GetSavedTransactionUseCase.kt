package com.example.loginactivity.feature.transaction.domain.usecases

import com.example.loginactivity.feature.transaction.domain.TransactionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSavedTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getSavedTransactions(driverId: Int) = withContext(dispatcher) {
        transactionRepository.getAllTransactions(driverId)
    }
}