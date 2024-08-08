package com.example.bvddriverfleetapp.feature.auth.domain.usecase

import com.example.bvddriverfleetapp.data.retrofit.Resource
import com.example.bvddriverfleetapp.feature.auth.data.model.LoginResponse
import com.example.bvddriverfleetapp.feature.auth.domain.repository.LoginRepository
import javax.inject.Inject

class ValidateUserEmailUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun verifyUserEmail(email: String): Resource<LoginResponse> {
        return loginRepository.loginUser(email)
    }
}