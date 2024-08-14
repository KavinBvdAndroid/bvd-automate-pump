package com.example.loginactivity.feature.pumpoperation.domain.usecase

import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveTransactionUseCase @Inject constructor(
    private val pumpOperationRepository: PumpOperationRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun saveTransactionRemote(request: SaveTransactionDto): TransactionSaveResult =
        withContext(dispatcher) {
            pumpOperationRepository.saveTransactionRemote(request)
        }
}