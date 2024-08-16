package com.example.loginactivity.feature.maps.domain.model

import com.example.loginactivity.feature.maps.data.model.ContactsItem
import com.example.loginactivity.feature.maps.data.model.InyardSiteAccessDetails
import com.example.loginactivity.feature.maps.data.model.InyardTanksItem
import com.example.loginactivity.feature.maps.data.model.State
import com.example.loginactivity.feature.maps.data.model.TimingsItem

data class FuelSite(val longitudeDirection: String = "N/A",
                    val inyardTanks: List<InyardTanksItem> = emptyList(),
                    val inYard: Int = 0,
                    val city: String = "N/A",
                    val latitude: Double = 0.0,
                    val createdAt: String = "N/A",
                    val latitudeDirection: String = "N/A",
                    val number: String = "N/A",
                    val updatedAt: String = "N/A",
                    val timings: List<TimingsItem> = emptyList(),
                    val id: Int = 0,
                    val stateId: Int = 0,
                    val state: State = State(),
                    val longitude: Double = 0.0,
                    val zip: String = "N/A",
                    val inyardSiteAccessDetails: InyardSiteAccessDetails = InyardSiteAccessDetails(),
                    val hours: String = "N/A",
                    val address: String = "N/A",
                    val inyardSiteType: String = "N/A",
                    val deleted: Int = 0,
                    val groupNumber: String = "N/A",
                    val phone: String = "N/A",
                    val name: String = "N/A",
                    val manned: Int = 0,
                    val countryId: Int = 0,
                    val contacts: List<ContactsItem> = emptyList(),
                    val status: Int = 0)
