package com.example.bvddriverfleetapp.feature.auth.data.RepositoryImpls

import com.example.bvddriverfleetapp.core.base.BaseRepository
import com.example.bvddriverfleetapp.data.retrofit.LoginApiService
import com.example.bvddriverfleetapp.data.retrofit.Resource
import com.example.bvddriverfleetapp.feature.auth.data.model.LoginResponse
import com.example.bvddriverfleetapp.feature.auth.domain.repository.LoginRepository
import com.google.gson.Gson
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val gson:Gson,private val apiService: LoginApiService) :
    LoginRepository, BaseRepository(gson) {

    override suspend fun loginUser(email:String): Resource<LoginResponse> {

        return safeApiCall<LoginResponse>(
            apiCall = {
                apiService.loginUser("testapp1", "Testapp1@123")
            },
            successType = LoginResponse::class.java,
            handleSuccess = {}
        )
    }
}
