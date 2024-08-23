package com.example.loginactivity.core.base.testdatas

import com.example.loginactivity.feature.maps.data.model.ContactsItem
import com.example.loginactivity.feature.maps.data.model.Data
import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.maps.data.model.FetchInYardSitesResponse
import com.example.loginactivity.feature.maps.data.model.FuelTaxesItem
import com.example.loginactivity.feature.maps.data.model.TimingsItem
import com.example.loginactivity.feature.pumpoperation.data.model.InyardSiteAccessDetails
import com.example.loginactivity.feature.pumpoperation.data.model.InyardTanksItem
import com.example.loginactivity.feature.pumpoperation.data.model.SiteDetails
import com.example.loginactivity.feature.pumpoperation.data.model.State
import com.example.loginactivity.feature.pumpoperation.data.model.TankSite
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.google.android.gms.maps.model.LatLng


val driverLocation = LatLng(43.7197104, -79.6870871)

val tankSite1 = TankSite(
    tankId = "T1",
    fuelType = "Diesel",
    tankCapacity = 5000.0
)

val tankSite2 = TankSite(
    tankId = "T2",
    fuelType = "Petrol",
    tankCapacity = 6000.0
)
val tankSite3 = TankSite(
    tankId = "T3",
    fuelType = "Petrol",
    tankCapacity = 6000.0
)
val tankSite4 = TankSite(
    tankId = "T4",
    fuelType = "Petrol",
    tankCapacity = 6000.0
)

val SiteDetails1 = SiteDetails(
    name = "Circle K",
    latitude = 43.713490,
    longitude = -79.679980,
    address = "2439 Steeles Ave E, Brampton, ON L6T 5J9",
    siteId = "S1",
    tankSites = listOf(
        TankSite(tankId = "T1", fuelType = "Diesel", tankCapacity = 5000.0),
        TankSite(tankId = "T2", fuelType = "Petrol", tankCapacity = 6000.0),
        TankSite(tankId = "T3", fuelType = "CNG", tankCapacity = 4000.0),
        TankSite(tankId = "T4", fuelType = "LPG", tankCapacity = 4500.0)
    ),
    distanceToLocation = 15.5
)

val SiteDetails2 = SiteDetails(
    name = "Petro Canada",
    latitude = 43.717810,
    longitude = -79.683640,
    address = "8155 Torbram Rd, Brampton, ON L6T 5C5",
    siteId = "S2",
    tankSites = listOf(
        TankSite(tankId = "T5", fuelType = "Diesel", tankCapacity = 5200.0),
        TankSite(tankId = "T6", fuelType = "Petrol", tankCapacity = 6100.0),
        TankSite(tankId = "T7", fuelType = "CNG", tankCapacity = 4300.0),
        TankSite(tankId = "T8", fuelType = "LPG", tankCapacity = 4700.0)
    ),
    distanceToLocation = 10.0
)

val SiteDetails3 = SiteDetails(
    name = "Econo Gas",
    latitude = 43.712311,
    longitude = -79.655121,
    address = "7454 Airport Rd, Mississauga, ON L4T 2H5",
    siteId = "S3",
    tankSites = listOf(
        TankSite(tankId = "T9", fuelType = "Diesel", tankCapacity = 5300.0),
        TankSite(tankId = "T10", fuelType = "Petrol", tankCapacity = 6200.0),
        TankSite(tankId = "T11", fuelType = "CNG", tankCapacity = 4400.0),
        TankSite(tankId = "T12", fuelType = "LPG", tankCapacity = 4800.0)
    ),
    distanceToLocation = 20.5
)

val SiteDetails4 = SiteDetails(
    name = "Esso",
    latitude = 43.722790,
    longitude = -79.718030,
    address = "1707 Queen St E, Brampton, ON L6T 2H2",
    siteId = "S4",
    tankSites = listOf(
        TankSite(tankId = "T13", fuelType = "Diesel", tankCapacity = 5400.0),
        TankSite(tankId = "T14", fuelType = "Petrol", tankCapacity = 6300.0),
        TankSite(tankId = "T15", fuelType = "CNG", tankCapacity = 4500.0),
        TankSite(tankId = "T16", fuelType = "LPG", tankCapacity = 4900.0)
    ),
    distanceToLocation = 25.0
)

val SiteDetails5 = SiteDetails(
    name = "Ultramar",
    latitude = 43.743120,
    longitude = -79.697030,
    address = "2956 Queen St E, Brampton, ON L6S 5Y1",
    siteId = "S5",
    tankSites = listOf(
        TankSite(tankId = "T17", fuelType = "Diesel", tankCapacity = 5500.0),
        TankSite(tankId = "T18", fuelType = "Petrol", tankCapacity = 6400.0),
        TankSite(tankId = "T19", fuelType = "CNG", tankCapacity = 4600.0),
        TankSite(tankId = "T20", fuelType = "LPG", tankCapacity = 5000.0)
    ),
    distanceToLocation = 30.0


)

val listOfSites = listOf(SiteDetails1, SiteDetails2, SiteDetails3, SiteDetails4, SiteDetails5)
val sortedListOfSites = listOfSites.sortedBy { it.distanceToLocation }

// Creating a test data object
val testInyardTanksItem = InyardTanksItem(
    safeFillLimit = 85,
    tankType = "Diesel",
    tankId = "T12345",
    tankDescription = "Main Diesel Tank",
    createdAt = "2023-07-15T10:00:00Z",
    fuelSiteId = 101,
    productCode = "DIESEL123",
    capacity = 10000,
    updatedAt = "2024-01-01T10:00:00Z",
    inventoryOwnedBy = "Company A",
    inventorySystem = "System B",
    id = 1,

    )

val testInyardTanksItem2 = InyardTanksItem(
    safeFillLimit = 90,
    tankType = "Petrol",
    tankId = "T67890",
    tankDescription = "Main Petrol Tank",
    createdAt = "2023-08-01T11:00:00Z",
    fuelSiteId = 102,
    productCode = "PETROL456",
    capacity = 15000,
    updatedAt = "2024-02-01T11:00:00Z",
    inventoryOwnedBy = "Company B",
    inventorySystem = "System C",
    id = 2,

    )

val testInyardTanksItem3 = InyardTanksItem(
    safeFillLimit = 80,
    tankType = "CNG",
    tankId = "T54321",
    tankDescription = "Main CNG Tank",
    createdAt = "2023-09-10T09:30:00Z",
    fuelSiteId = 103,
    productCode = "CNG789",
    capacity = 12000,
    updatedAt = "2024-03-01T09:30:00Z",
    inventoryOwnedBy = "Company C",
    inventorySystem = "System D",
    id = 3,

    )

val testInyardTanksItem4 = InyardTanksItem(
    safeFillLimit = 75,
    tankType = "LPG",
    tankId = "T98765",
    tankDescription = "Main LPG Tank",
    createdAt = "2023-10-05T08:00:00Z",
    fuelSiteId = 104,
    productCode = "LPG012",
    capacity = 13000,
    updatedAt = "2024-04-01T08:00:00Z",
    inventoryOwnedBy = "Company D",
    inventorySystem = "System E",
    id = 4,

    )

val testInyardTanksItem5 = InyardTanksItem(
    safeFillLimit = 95,
    tankType = "Kerosene",
    tankId = "T11223",
    tankDescription = "Main Kerosene Tank",
    createdAt = "2023-11-20T07:45:00Z",
    fuelSiteId = 105,
    productCode = "KEROSENE345",
    capacity = 14000,
    updatedAt = "2024-05-01T07:45:00Z",
    inventoryOwnedBy = "Company E",
    inventorySystem = "System F",
    id = 5,

    )

val requestTransaction = TransactionDto(
    vinNumber = 1234,
    transactionType = "bulk",
    inyardSiteId = 1234,
    fuelCode = "DSL",
    quantity = 388.77,
    cardNumber = 123454
)

val requestTransaction1 = TransactionDto(
    vinNumber = 2345,
    transactionType = "bulk",
    inyardSiteId = 3333,
    fuelCode = "DSL",
    quantity = 4000.77,
    cardNumber = 8383838
)

val requestTransaction2 = TransactionDto(
    vinNumber = 3344,
    transactionType = "bulk",
    inyardSiteId = 2222,
    fuelCode = "DSL",
    quantity = 400.77,
    cardNumber = 333454
)
val listOfInYardItems = listOf(
    testInyardTanksItem, testInyardTanksItem2, testInyardTanksItem3, testInyardTanksItem4,
    testInyardTanksItem5
)
val mockToken =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMTZmYTlmNWY2NDhkNWRhOWNhYmVkYTgyMTJlZDA2ODQ1YTZkYTc2ZmU4YTc4YzI4NzQ5M2Q5NDY0ZDJkNTg5NDQzZDFjZDJlOGUyYjI3YzAiLCJpYXQiOjE3MjQ0MjEyNDguMzkyNDQ4LCJuYmYiOjE3MjQ0MjEyNDguMzkyNDQ5LCJleHAiOjE3MjQ1MDc2NDguMzg1ODcyLCJzdWIiOiI1NjI1Iiwic2NvcGVzIjpbXX0.Q3d5xpd2zPH6WzuA8IxaDlQTwulvrBmr081jV8W6HVpm1AYnnaQnEXDWOeusZX69BvmWIrI3dZv5ui0MEs3_8KTZla5M4FnbyA0wDxjfhmUbHANmkC7WhhCNgEh5kH7GiEKAwD10ep1Szer8sdjtTGWprFj94u8oQGXLXNPrSYshJrv4gB9UYbcmSLtGSvRkGblDTN9_OGPSn9atTvkF2-UghTDZcFU5aPt4YS8JiWM3qYyrxNy7lJMyiHofHUCSbT5g800RpAOuSqRodKUa1y9HZp-uJ1ll5W0VrhPjvNXziCJYUtcft8kIK36WDFB0CduxCoOXDmRb31s3eChAHIT_Nz8Xc4ewBCmIhl67VvZoqPtgJuXPW1npgwuWk6ZzjTAr71iL09D_Kro_4iyrj2m0E_YZCQVkilmH-V0NlkTSFOAdKPoyXm1XZ4c_5h5YLi4M9_HEhVywgzLyWmcOluckqwWg3vvdOLCZ84DwlbobHcwsJ5L7HOam6XI8FMsYkO2e5AUPpeoKPuzGgjqtaGL10MAtR0SZ9ajnjBzlAQ3swt4-NJXNNAjVOzK_1_9OkXsRsweerCms-wLaE5aZ2MR4xwuK_T-qkwviAExmWZzwJ77Eel3ItyAFwWPUIVv7DC3nECVirDJUSLbsvkd8MEcM81d4Klsuy3Sicx9DwAs"

val mockFetchInYardSitesResponse = FetchInYardSitesResponse(
    code = 200,
    message = "Successfully pulled the records.",
    error = false,
    data = Data(
        perPage = 10,
        currentPage = 1,
        lastPage = 1,
        total = 6,
        data = listOf(
            DataItem(
                id = 15,
                stateId = 52,
                countryId = 2,
                number = "987720",
                name = "Ultra Mar",
                groupNumber = "97000",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8170 Torbram Road",
                phone = "1234567890",
                zip = "12340",
                latitude = 43.743120,
                longitude = -79.697030,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 50,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 1,
                    fuelSiteId = 1,
                    accessType = "FOB READER",
                    accessDetail = "Detail 0",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 1,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 1,
                        siteId = 1,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567890",
                        email = "john.doe0@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 1,
                        siteId = 1,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            ), DataItem(
                id = 12,
                stateId = 52,
                countryId = 2,
                number = "987720",
                name = "Ultra Mar",
                groupNumber = "97000",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8170 Torbram Road",
                phone = "1234567890",
                zip = "12340",
                latitude = 43.743120,
                longitude = -79.697030,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 40,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 1,
                    fuelSiteId = 1,
                    accessType = "FOB READER",
                    accessDetail = "Detail 0",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 1,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 1,
                        siteId = 1,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567890",
                        email = "john.doe0@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 1,
                        siteId = 1,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            ), DataItem(
                id = 1,
                stateId = 52,
                countryId = 2,
                number = "987720",
                name = "Ultra Mar",
                groupNumber = "97000",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8170 Torbram Road",
                phone = "1234567890",
                zip = "12340",
                latitude = 43.743120,
                longitude = -79.697030,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 30,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 1,
                    fuelSiteId = 1,
                    accessType = "FOB READER",
                    accessDetail = "Detail 0",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 1,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 1,
                        siteId = 1,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567890",
                        email = "john.doe0@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 1,
                        siteId = 1,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            ),
            DataItem(
                id = 2,
                stateId = 52,
                countryId = 2,
                number = "987721",
                name = "Pioneer",
                groupNumber = "97001",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8171 Torbram Road",
                phone = "1234567891",
                zip = "12341",
                latitude = 43.722790,
                longitude = -79.718030,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 25,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 2,
                    fuelSiteId = 2,
                    accessType = "FOB READER",
                    accessDetail = "Detail 1",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 2,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 2,
                        siteId = 2,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567891",
                        email = "john.doe1@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 2,
                        siteId = 2,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            ),
            DataItem(
                id = 3,
                stateId = 52,
                countryId = 2,
                number = "987722",
                name = "Esso",
                groupNumber = "97002",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8172 Torbram Road",
                phone = "1234567892",
                zip = "12342",
                latitude = 43.712311,
                longitude = -79.655121,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 20,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 3,
                    fuelSiteId = 3,
                    accessType = "FOB READER",
                    accessDetail = "Detail 2",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 3,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 3,
                        siteId = 3,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567892",
                        email = "john.doe2@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 3,
                        siteId = 3,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            ),
            DataItem(
                id = 4,
                stateId = 52,
                countryId = 2,
                number = "987723",
                name = "Petro Canada",
                groupNumber = "97003",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8173 Torbram Road",
                phone = "1234567893",
                zip = "12343",
                latitude = 43.717810,
                longitude = -79.683640,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 10,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 4,
                    fuelSiteId = 4,
                    accessType = "FOB READER",
                    accessDetail = "Detail 3",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 4,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 4,
                        siteId = 4,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567893",
                        email = "john.doe3@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 4,
                        siteId = 4,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            ),
            DataItem(
                id = 5,
                stateId = 52,
                countryId = 2,
                number = "987724",
                name = "Circle K",
                groupNumber = "97004",
                manned = 0,
                inYard = 1,
                inyardSiteType = "DELIVERED_BY_BVD",
                status = 1,
                city = "Brampton",
                address = "8174 Torbram Road",
                phone = "1234567894",
                zip = "12344",
                latitude = 43.713490,
                longitude = -79.679980,
                latitudeDirection = "N",
                longitudeDirection = "W",
                hours = "9 AM - 5 PM",
                deleted = 0,
                createdAt = "2024-07-10T14:28:31.000000Z",
                updatedAt = "2024-08-22T14:43:22.000000Z",
                distance = 15,
                inyardSiteAccessDetails = InyardSiteAccessDetails(
                    id = 5,
                    fuelSiteId = 5,
                    accessType = "FOB READER",
                    accessDetail = "Detail 4",
                    createdAt = "2024-07-12T17:40:22.000000Z",
                    updatedAt = "2024-07-12T17:40:22.000000Z"
                ),
                inyardTanks = List(6) { tankIndex ->
                    com.example.loginactivity.feature.maps.data.model.InyardTanksItem(
                        id = tankIndex + 1,
                        fuelSiteId = 5,
                        tankId = "JJ997${tankIndex}H",
                        productCode = "B15UL2SB",
                        tankDescription = "Dyed Diesel Tank $tankIndex",
                        capacity = 1200 + (tankIndex * 100),
                        safeFillLimit = 1000 + (tankIndex * 100),
                        tankType = "AG(WITH OFF LOAD PUMP)",
                        inventorySystem = "VECTOR ROOT",
                        inventoryOwnedBy = "BVD OWNED",
                        createdAt = "2024-08-22T14:45:46.000000Z",
                        updatedAt = "2024-08-22T14:45:46.000000Z",
                        fuelTaxes = listOf(
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "hst",
                                taxAmount = 0.13
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "qst",
                                taxAmount = 0.0975
                            ),
                            FuelTaxesItem(
                                inyardTankId = tankIndex + 1,
                                taxCode = "pst",
                                taxAmount = 0.06
                            )
                        )
                    )
                },
                state = State(
                    id = 52,
                    countryId = 2,
                    createdAt = "2013-03-20T23:48:59.000000Z",
                    updatedAt = "2013-03-20T23:48:59.000000Z",
                    name = "Ontario",
                    abbreviation = "ON"
                ),
                contacts = listOf(
                    ContactsItem(
                        id = 5,
                        siteId = 5,
                        title = "Manager",
                        firstName = "John",
                        lastName = "Doe",
                        phoneType = "office",
                        phoneNumber = "1234567894",
                        email = "john.doe4@example.com",
                        createdAt = "2024-07-10T18:09:06.000000Z",
                        updatedAt = "2024-07-10T18:09:06.000000Z"
                    )
                ),
                timings = listOf(
                    TimingsItem(
                        id = 5,
                        siteId = 5,
                        weekday = "Monday",
                        allDay = "YES",
                        startTime = null,
                        endTime = null,
                        createdAt = "2024-08-22T14:43:38.000000Z",
                        updatedAt = "2024-08-22T14:43:38.000000Z"
                    )
                )
            )
        )
    )
)