package com.example.loginactivity.feature.auth.data.model

import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.feature.auth.domain.LoginRepository
import com.google.gson.Gson
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val gson:Gson,private val apiService: LoginApiService) :
    LoginRepository, BaseRepository(gson) {

    override suspend fun loginUser(email:String, password:String): Resource<LoginResponse> {

        return safeApiCall<LoginResponse>(
            apiCall = {
                apiService.loginUser(email, password = password)
            },
            successType = LoginResponse::class.java,
            handleSuccess = {}
        )
    }
}
