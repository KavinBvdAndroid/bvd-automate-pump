package com.example.loginactivity.core.base.testdatas

import com.example.loginactivity.feature.pumpoperation.data.model.InyardTanksItem
import com.example.loginactivity.feature.pumpoperation.data.model.SiteDetails
import com.example.loginactivity.feature.pumpoperation.data.model.TankSite
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
val listOfInYardItems = listOf(
    testInyardTanksItem, testInyardTanksItem2, testInyardTanksItem3, testInyardTanksItem4,
    testInyardTanksItem5
)

val mockToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNzFlOWFiY2ZkNGE0MzViNjM0NjVmMmJkNGFmZGEzMmQyYmQ1ZTA2NjgyNDA4ODhjMThjY2QzNjlhNTY1Mjc2ZmE4MGI5OTMxMmJiOGMxM2YiLCJpYXQiOjE3MjM1NjY3NzYuNzkyMDE1LCJuYmYiOjE3MjM1NjY3NzYuNzkyMDE3LCJleHAiOjE3MjM2NTMxNzYuNzg1MjI2LCJzdWIiOiI1NjI1Iiwic2NvcGVzIjpbXX0.gL3odE2zVYqxodNdyr4N1YJxVdkG42r1Nz5ixRl3fXUW7tiLx21tjV6PDbveP0LKHyIzG31YuLy0CpgffZ7VGCqCwrl5Ni8hT6l7mgurY-6x_XJSZFoEbcgWjM7tVCyM2O0hGFZcifScWcwKb4FAhHL_q4WotxznOjpHzf-aZoCaiUcBJTtwsUWe-XG9Qk90mGwNMbUb7XwcJPPaXW04kGGCDP4Pyivck0cB3Il44mkwJr7o8GN1sajGAqDeMgRIahSqFnAD7__n32IkHCwm_oKVmZ7Y5dFgAuZdvS1YMvi_pPFCqkIKoDhCwhnV8MXY5GJ-uWoLT2arT-B601zUoHLIXpgO1ANNbA7X2f7WmPEZHQhp7RgeFSYpYV7a-C28P4VpQXM7Fyff6fHKWp7BzEL0xl6mxL6H0B291WrX-eSNeNs8OOOSbqBk9vA6tp65yncoYF0oD0_iOVZuNp1pTlNAPqZlVJ40caMFwy_qwkgMcBwKurMnfPfJKfrZjrV0gn2RC5ft27sA30h01yEeZwPftUg-pHDCuCpFr0E0Jn92aUX8Tv5Y47tsuw7z3R-Ei99wkQcWLcoznNWgHoxaHJlAK1TZe5Lh3mQpA0-1faV8IcqeBgRF4uomsP0eFlhKZrAHO5nonUM6KXr0yQC3_0pShWgVROb37wcIXhBezME"