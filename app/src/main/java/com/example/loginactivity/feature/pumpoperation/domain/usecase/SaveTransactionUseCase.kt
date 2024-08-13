package com.example.loginactivity.feature.pumpoperation.domain.usecase

import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository
import javax.inject.Inject

class SaveTransactionUseCase @Inject constructor(private  val pumpOperationRepository: PumpOperationRepository) {
    suspend fun saveTransactionRemote(request: SaveTransactionDto) : Resource<GenericBaseResponse>{
        return pumpOperationRepository.saveTransactionRemote(request)
    }
}