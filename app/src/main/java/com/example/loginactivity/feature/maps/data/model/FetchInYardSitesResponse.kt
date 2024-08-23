package com.example.loginactivity.feature.maps.data.model

import android.os.Parcelable
import com.example.loginactivity.feature.pumpoperation.data.model.InyardSiteAccessDetails
import com.example.loginactivity.feature.pumpoperation.data.model.State
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FetchInYardSitesResponse(
	val code: Int? = null,
	val data: Data? = null,
	val message: String? = null,
	val error: Boolean? = null
) : Parcelable

@Parcelize
data class DataItem(
	val longitudeDirection: String? = null,
	val inyardTanks: List<com.example.loginactivity.feature.maps.data.model.InyardTanksItem> = emptyList(),
	val inYard: Int? = null,
	val distance: Int? = null,
	val city: String? = null,
	val latitude: Double? = null,
	val createdAt: String? = null,
	val latitudeDirection: String? = null,
	val number: String? = null,
	val updatedAt: String? = null,
	val timings: List<TimingsItem?>? = null,
	val id: Int? = null,
	val stateId: Int? = null,
	val state: State? = null,
	val longitude: Double? = null,
	val zip: String? = null,
	val inyardSiteAccessDetails: InyardSiteAccessDetails? = null,
	val hours: String? = null,
	val address: String? = null,
	val inyardSiteType: String? = null,
	val deleted: Int? = null,
	val groupNumber: String? = null,
	val phone: String? = null,
	val name: String? = null,
	val manned: Int? = null,
	val countryId: Int? = null,
	val contacts: List<ContactsItem?>? = null,
	val status: Int? = null
) : Parcelable

@Parcelize
data class State(
	val updatedAt: String? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val abbreviation: String? = null,
	val countryId: Int? = null
) : Parcelable

@Parcelize
data class ContactsItem(
	val phoneType: String? = null,
	val updatedAt: String? = null,
	val siteId: Int? = null,
	val lastName: String? = null,
	val createdAt: String? = null,
	val phoneNumber: String? = null,
	val id: Int? = null,
	val title: String? = null,
	val firstName: String? = null,
	val email: String? = null
) : Parcelable

@Parcelize
data class InyardSiteAccessDetails(
	val accessType: String? = null,
	val updatedAt: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val fuelSiteId: Int? = null,
	val accessDetail: String? = null
) : Parcelable

@Parcelize
data class Data(
	val perPage: Int? = null,
	val data: List<DataItem?>? = null,
	val lastPage: Int? = null,
	val nextPageUrl: String? = null,
	val prevPageUrl: String? = null,
	val firstPageUrl: String? = null,
	val path: String? = null,
	val total: Int? = null,
	val lastPageUrl: String? = null,
	val from: Int? = null,
	val links: List<LinksItem?>? = null,
	val to: Int? = null,
	val currentPage: Int? = null
) : Parcelable

@Parcelize
data class LinksItem(
	val active: Boolean? = null,
	val label: String? = null,
	val url: String? = null
) : Parcelable

@Parcelize
data class FuelTaxesItem(
	val taxAmount: Double? = null,
	val taxCode: String? = null,
	val inyardTankId: Int? = null
) : Parcelable

@Parcelize
data class TimingsItem(
	val startTime: String? = null,
	val updatedAt: String? = null,
	val siteId: Int? = null,
	val weekday: String? = null,
	val allDay: String? = null,
	val endTime: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
) : Parcelable

@Parcelize
data class InyardTanksItem(
	val safeFillLimit: Int? = null,
	val tankType: String? = null,
	val tankId: String? = null,
	val tankDescription: String? = null,
	val createdAt: String? = null,
	val fuelSiteId: Int? = null,
	val productCode: String? = null,
	val capacity: Int? = null,
	val updatedAt: String? = null,
	val inventoryOwnedBy: String? = null,
	val inventorySystem: String? = null,
	val id: Int? = null,
	val fuelTaxes: List<FuelTaxesItem?>? = null
) : Parcelable
