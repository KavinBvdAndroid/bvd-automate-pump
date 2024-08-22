package com.example.loginactivity.feature.transaction.data

import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionEntity

fun TransactionEntity.toDto(): TransactionDto {
    return TransactionDto(
       driverId = this.driverId,
        truckId = this.truckId,
        transactionType = this.transactionType,
        inyardSiteId = this.inyardSiteId,
        cardNumber = this.cardNumber,
        vinNumber = this.cardNumber,
        quantity = this.quantity,
        fuelCode = this.fuelCode
        // Map other fields as necessary
    )
}

fun TransactionDto.toEntity(isSyncedToRemote: Boolean = false): TransactionEntity {
    return TransactionEntity(
        truckId = this.truckId,
        driverId = this.driverId,
        fuelCode = this.fuelCode,
        quantity = this.quantity,
        cardNumber = this.cardNumber,
        vinNumber = this.vinNumber,
        transactionType = this.transactionType,
        inyardSiteId = this.inyardSiteId,
        isSyncedToRemote = isSyncedToRemote
    )
}