package com.example.loginactivity.feature.pumpoperation.save

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transactions")
data class SaveTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "fuel_code")
    var fuelCode: String? = null,

    @ColumnInfo(name = "quantity")
    var quantity: Double? = null,

    @ColumnInfo(name = "card_number")
    var cardNumber: Int? = null,

    @ColumnInfo(name = "vin_number")
    var vinNumber: Int? = null,

    @ColumnInfo(name = "transaction_type")
    val transactionType: String? = null,

    @ColumnInfo(name = "inyard_site_id")
    var inyardSiteId: Int? = null,

    @ColumnInfo(name = "is_synced_to_remote")
    var isSyncedToRemote: Boolean = false

)