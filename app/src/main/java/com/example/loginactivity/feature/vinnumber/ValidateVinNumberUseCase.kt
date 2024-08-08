package com.example.loginactivity.feature.vinnumber

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.auth.data.model.LoginResponse
import javax.inject.Inject

class ValidateUserEmailUseCase @Inject constructor(private val vinNumberRepository: VinNumberRepository) {

    suspend fun verifyUserEmail(vinNumber:String): Resource<VinNumberResponse> {
        return vinNumberRepository.verifyVin(vinNumber)
    }
}