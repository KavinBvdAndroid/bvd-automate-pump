package com.example.bvddriverfleetapp.feature.auth.domain.repository

import com.example.bvddriverfleetapp.core.base.BaseRepository
import com.example.bvddriverfleetapp.data.retrofit.Resource
import com.example.bvddriverfleetapp.data.retrofit.UserDetails
import com.example.bvddriverfleetapp.feature.auth.data.model.LoginResponse

interface LoginRepository {
    suspend fun loginUser(email: String): Resource<LoginResponse>
}