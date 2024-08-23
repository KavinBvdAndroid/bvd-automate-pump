package com.example.loginactivity.feature.vinnumber

import android.util.Log
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.google.gson.Gson
import javax.inject.Inject

class VinNumberRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val apiService: LoginApiService,
    private val sessionManager: SessionManager
) :
    VinNumberRepository, BaseRepository(gson) {

    override suspend fun verifyVinNumber(vinNumber: String): Resource<VinNumberResponse> {
        return safeApiCall(
            apiCall = {
                apiService.validateVinNumber(vinNumber)
            },
            successType = VinNumberResponse::class.java,
            handleSuccess = {
                Log.d("vin number", "" + it.toString())
                if (it.has("vin_number")) {
                    saveTruckID(it.get("vin_number").asString)
                }
            },
            handleFailure = {

            }
        )
    }

    private fun saveTruckID(asString: String?) {
        if (asString != null) {
            sessionManager.saveTruckId(asString)
        }

    }


}
