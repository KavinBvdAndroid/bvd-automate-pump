package com.example.loginactivity.feature.pumpoperation.save

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SaveTransactionDto(
	val fuelCode: String? = null,
	val quantity: Double? = null,
	val cardNumber: Int? = null,
	val vinNumber: Int? = null,
	val transactionType: String? = null,
	val inyardSiteId: Int? = null
) : Parcelable
