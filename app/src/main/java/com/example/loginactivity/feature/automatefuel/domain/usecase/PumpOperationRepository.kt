package com.example.loginactivity.feature.automatefuel.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse

interface PumpOperationRepository {
    suspend fun operatePump(cmd: String, params: String): Resource<PumpResponse>
}