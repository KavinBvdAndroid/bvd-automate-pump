package com.example.loginactivity.feature.automatefuel.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse
import javax.inject.Inject

class StartPumpUseCase @Inject constructor(val pumpOperationRepository: PumpOperationRepository) {

    suspend fun startActivatingPump(cmd:String, params: String) : Resource<PumpResponse>{
        return pumpOperationRepository.operatePump(cmd,params)
    }

}