package com.example.loginactivity.feature.pumpoperation.save

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transactions")
data class SaveTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val fuelCode: String? = null,
    val quantity: Double? = null,
    val cardNumber: Int? = null,
    val vinNumber: Int? = null,
    val transactionType: String? = null,
    val inyardSiteId: Int? = null,
    val timestamp: Long,
    val isSuccessfullySynced: Boolean = false

)