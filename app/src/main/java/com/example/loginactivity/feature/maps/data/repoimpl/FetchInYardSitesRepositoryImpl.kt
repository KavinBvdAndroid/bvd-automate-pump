package com.example.loginactivity.feature.maps.data.repoimpl

import android.util.Log
import com.example.loginactivity.core.base.BaseRepository
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.retrofit.LoginApiService
import com.example.loginactivity.feature.maps.data.model.FetchInYardSitesResponse
import com.example.loginactivity.feature.maps.domain.FetchInYardSitesRepository
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import javax.inject.Inject

class FetchInYardSitesRepositoryImpl @Inject constructor(private val gson: Gson, private val apiService: LoginApiService) : FetchInYardSitesRepository, BaseRepository(gson) {
    override suspend fun fetchNearestSites(driverLocation: LatLng?): Resource<FetchInYardSitesResponse> {
        return safeApiCall(
            apiCall = { apiService.fetchInYardItems() },
            successType = FetchInYardSitesResponse::class.java,
            handleSuccess = {
                Log.d("Api Success Response"," FetchInYardResponse ${it}")
            }
        )
    }
}