package com.example.loginactivity.feature.pumpoperation.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PumpOperation(
	val cmd: String? = null,
	val params: String? = null
) : Parcelable
