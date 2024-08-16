package com.example.loginactivity.feature.pumpoperation.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.domain.repo.PumpOperationRepository
import javax.inject.Inject

class StartPumpUseCase @Inject constructor(val pumpOperationRepository: PumpOperationRepository) {

    suspend fun startActivatingPump(cmd:String, params: String) : Resource<PumpResponse>{
        return pumpOperationRepository.operatePump(cmd,params)
    }

}