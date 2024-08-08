package com.example.loginactivity.feature.vinnumber

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
	val code: Int? = null,
	val data: List<DataItem?>? = null,
	val message: String? = null,
	val error: Boolean? = null
) : Parcelable

@Parcelize
data class DataItem(
	val unitNumber: String? = null,
	val updatedAt: String? = null,
	val year: Int? = null,
	val vinNumber: String? = null,
	val createdAt: String? = null,
	val model: String? = null,
	val id: Int? = null,
	val make: String? = null,
	val deletedAt: Any? = null
) : Parcelable
