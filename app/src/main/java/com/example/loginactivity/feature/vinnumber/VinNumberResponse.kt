package com.example.loginactivity.feature.vinnumber

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VinNumberResponse(
	val code: Int? = null,
	val data: List<DataItem?>? = null,
	val message: String? = null,
	val error: Boolean? = null
) : Parcelable

@Parcelize
data class DataItem(
	val unit_number: String? = null,
	val updated_at: String? = null,
	val year: Int? = null,
	val vin_number: String? = null,
	val created_at: String? = null,
	val model: String? = null,
	val id: Int? = null,
	val make: String? = null,
	val deleted_at: String? = null
) : Parcelable
