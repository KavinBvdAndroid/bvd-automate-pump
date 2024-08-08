package com.example.loginactivity.feature.vinnumber

import android.util.Log
import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.LoginLogo
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.data.retrofit.VinNumberApiService
import com.google.gson.Gson
import javax.inject.Inject

class VinNumberRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val apiService: VinNumberApiService
) :
    VinNumberRepository, BaseRepository(gson) {

    override suspend fun verifyVinNumber(vinNumber: String): Resource<VinNumberResponse> {
        return safeApiCall(
            apiCall = {
                apiService.validateVinNumber(vinNumber)
            },
            successType = VinNumberResponse::class.java,
            handleSuccess = {
                Log.d("vin number",""+it.toString())
            }
        )
    }


}
