package com.example.loginactivity.feature.auth.domain

import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.auth.data.model.LoginResponse
import javax.inject.Inject

class ValidateUserEmailUseCase @Inject constructor(private val loginRepository: LoginRepository
) {

    suspend fun verifyUserEmail(email: String,password:String): Resource<LoginResponse> {
        return loginRepository.loginUser(email,password)
    }

    fun setLoggedIn(){
        loginRepository.saveLoginState(true)
    }

    fun setLoggedOut(){
        loginRepository.logOut()
    }
}