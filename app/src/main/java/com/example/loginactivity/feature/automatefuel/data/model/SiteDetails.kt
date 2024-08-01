package com.example.loginactivity.feature.automatefuel.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SiteDetails(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val siteId: String,
    val tankSites: List<TankSite>,
    val distanceToLocation: Double
) : Parcelable
