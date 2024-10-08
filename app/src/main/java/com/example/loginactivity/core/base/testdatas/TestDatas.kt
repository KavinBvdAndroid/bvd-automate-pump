package com.example.loginactivity.core.base.testdatas

import com.example.loginactivity.feature.pumpoperation.data.model.InyardTanksItem
import com.example.loginactivity.feature.pumpoperation.data.model.SiteDetails
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
    cardNumber =333454
)
val listOfInYardItems = listOf(
    testInyardTanksItem, testInyardTanksItem2, testInyardTanksItem3, testInyardTanksItem4,
    testInyardTanksItem5
)

val mockToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMmM0OGM0ZWUzMGUwNmFiMmJiNGU5MWQyNWI3NDI1NjY4M2Q1Mzc3YTFkMWFlZjJlZjI4ODBkZjhjMTE4ZTcwYzI1MzQ4YjJmNmQzNTIxNDUiLCJpYXQiOjE3MjM3NDIzMTAuMzA2OTU4LCJuYmYiOjE3MjM3NDIzMTAuMzA2OTYsImV4cCI6MTcyMzgyODcxMC4yOTk0NjEsInN1YiI6IjU2MjUiLCJzY29wZXMiOltdfQ.TKIWBYcpBvn1ujKeYwfpytPASaUs2kuVFs4L6hgZK4HXOSRQUzHl9RL1CIfXd2MVaBg95HN6jAFQBJ15IP9hZ0O9kDtnnjQOZfWtbvKIffOAWjAIQncQ4lLarv7qpdHee3i25m_7sN8N1-ch385dnaanRoqliAJqVtdOlDSRxYr-AAfbagAp5vromrOkqQnj0mEPstpK-EUBqEOjCXXeo8_gtzi7RkrTx-xABAbBwhVu3vmHbz6Vt88VX1hCcqJVwQUMFeVPNYNzdaanKiuyL7Ph2mwALtcfE9RS1kYSkRMgk1dF_nZCx8NM9-EzGWgbRiGYhHGwhYnINSTnjHiEeifT0zoAuX6xeGeA6cpYngpaqfu0f3zgKH2WsKs9mHstG-Uk8yW04ajTCnZgPckuo5nTl4hri5PdlInwcfRJJxUP7s-WDCcJhE_P5P9TrIp6oLDyq6L8-qu6r02KDd4-nrVWBYXPrAyWTqSXRtrAzWHIkoUB4XR3GRI7fYf-1fTAjTz-5svnR6rpbWTj-8ctVteYklqtWiSqUNpWAIW00Jt_SzaaF05eQRqEzaIpW0vaJQ4w3ylJOcBukQkXGhCXLJyk6QSNOQljpAiB6gokBTI83uN2FPznKGQDQrIS6-Vyov6BQwtw_0n508LC9X0fxSISMSOFN2Ot6NBvQ5uzXNs"