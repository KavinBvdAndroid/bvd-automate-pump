package com.example.loginactivity.feature.pumpoperation.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TankSite(val tankId: String,
                    val fuelType: String,
                    val tankCapacity: Double) : Parcelable
