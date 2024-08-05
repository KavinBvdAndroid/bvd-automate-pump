package com.example.loginactivity.feature.automatefuel.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse

class StopPumpUseCase(private val pumpOperationRepository: PumpOperationRepository) {

    suspend fun stopActivatingPump( cmd:String, params: String) : Resource<PumpResponse> {
        return pumpOperationRepository.operatePump(cmd, params)
    }
}