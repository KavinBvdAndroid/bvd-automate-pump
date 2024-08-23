package com.example.loginactivity.feature.transaction.domain.usecases

import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.domain.TransactionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//class SaveTransactionUseCase @Inject constructor(
//    private val transactionRepository: TransactionRepository,
//    private val sessionManager: SessionManager,
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
//) {
//    suspend fun saveTransactionRemote(request: TransactionDto): TransactionSaveResult =
//        withContext(dispatcher) {
//            transactionRepository.saveTransaction(request)
//        }
//}