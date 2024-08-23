package com.example.loginactivity.feature.transaction.domain

import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto

interface SaveTransactionValuesRepository {
    fun saveDriverId(driverId:String)
    fun saveTruckId(truckId:String)
    fun saveFuelCode(fuelCode:String)
    fun saveQuantity(qty:Double)
    fun saveTransactionType(type:String)
    fun saveInYardId(yardId:Int)
    fun saveCardNumber(cardNumber:Int)
     fun saveTransactionDto(transactionDto: DataItem)
}