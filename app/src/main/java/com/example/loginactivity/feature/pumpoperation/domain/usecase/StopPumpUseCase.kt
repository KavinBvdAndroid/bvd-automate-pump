package com.example.loginactivity.feature.pumpoperation.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository

class StopPumpUseCase(private val pumpOperationRepository: PumpOperationRepository) {

    suspend fun stopActivatingPump( cmd:String, params: String) : Resource<PumpResponse> {
        return pumpOperationRepository.operatePump(cmd, params)
    }
}