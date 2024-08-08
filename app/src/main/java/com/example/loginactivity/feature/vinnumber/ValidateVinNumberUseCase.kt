package com.example.loginactivity.feature.vinnumber

import com.example.loginactivity.core.base.generics.Resource
import javax.inject.Inject

class ValidateVinNumberUseCase @Inject constructor(private val vinNumberRepository: VinNumberRepository) {

    suspend fun verifyVinNumber(vinNumber:String): Resource<VinNumberResponse> {
        return vinNumberRepository.verifyVinNumber(vinNumber)
    }
}