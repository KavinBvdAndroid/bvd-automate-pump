package com.example.loginactivity.feature.auth.domain

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.auth.data.model.LoginResponse

interface LoginRepository {
    suspend fun loginUser(email: String, password:String): Resource<LoginResponse>
}