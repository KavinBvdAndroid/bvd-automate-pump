package com.example.loginactivity.feature.maps.domain.usecase

import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.maps.data.model.FetchInYardSitesResponse
import com.example.loginactivity.feature.maps.domain.FetchInYardSitesRepository
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class FetchInYardSiteUseCase @Inject constructor(private val fetchInYardSitesRepository: FetchInYardSitesRepository) {

    suspend fun fetchInYardSites(driverLocation: LatLng? = null) : Resource<FetchInYardSitesResponse>{
       return fetchInYardSitesRepository.fetchNearestSites(driverLocation)
    }
}