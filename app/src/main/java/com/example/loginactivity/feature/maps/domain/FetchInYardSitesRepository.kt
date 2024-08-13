package com.example.loginactivity.feature.maps.domain

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.maps.data.model.FetchInYardSitesResponse
import com.google.android.gms.maps.model.LatLng

interface FetchInYardSitesRepository {
    suspend fun fetchNearestSites(driverLocation: LatLng): Resource<FetchInYardSitesResponse>

}