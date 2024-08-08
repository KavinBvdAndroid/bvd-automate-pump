package com.example.loginactivity.feature.vinnumber

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.auth.data.model.LoginResponse

interface VinNumberRepository {
    suspend fun verifyVinNumber(vinNumber:String):  Resource<VinNumberResponse>
}