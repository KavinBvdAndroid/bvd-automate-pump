package com.example.loginactivity.feature.locationsites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.automatefuel.data.model.SiteDetails
import com.example.loginactivity.feature.automatefuel.data.model.sortedListOfSites
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuelSitesViewModel @Inject constructor(): ViewModel() {


    private val _mutableDriverLocation = MutableStateFlow(DriverLocation(0.0, 0.0))
    val driverLocationFlow : StateFlow<DriverLocation> = _mutableDriverLocation

    private val _fuelSiteDetails = MutableStateFlow<List<SiteDetails>>(sortedListOfSites)
    val fuelSiteDetails : StateFlow<List<SiteDetails>> = _fuelSiteDetails



    private val _sites = MutableStateFlow<List<SiteDetails>>(emptyList())
    val sites: StateFlow<List<SiteDetails>> = _sites

    private val _markerStates = MutableStateFlow<Map<String, MarkerState>>(emptyMap())
    val markerStates: StateFlow<Map<String, MarkerState>> = _markerStates


    fun saveUserLocation( latitude: Double, longitude: Double){
        _mutableDriverLocation.value = DriverLocation(latitude,longitude)
    }

    fun fetchNearBySites(latLng: LatLng){
        viewModelScope.launch {
            Resource.Loading

        }
    }

    fun updateSites(newSites: List<SiteDetails>) {
        _sites.value = newSites.sortedBy { it.latitude }
        _markerStates.value = newSites.associate { site ->
            site.siteId to MarkerState(position = LatLng(site.latitude, site.longitude))
        }
    }
}