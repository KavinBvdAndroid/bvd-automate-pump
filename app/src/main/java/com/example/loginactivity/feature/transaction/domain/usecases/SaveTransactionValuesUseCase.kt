package com.example.loginactivity.feature.transaction.domain.usecases

import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.domain.SaveTransactionValuesRepository
import javax.inject.Inject

class SaveTransactionValuesUseCase @Inject
constructor(private val saveTransactionValuesRepository: SaveTransactionValuesRepository) {

    fun saveDriverId(driverId:String){
        saveTransactionValuesRepository.saveDriverId(driverId)
    }

    fun saveTruckId(truckId:String){
        saveTransactionValuesRepository.saveTruckId(truckId)
    }

    fun saveFuelCode(driverId:String){
        saveTransactionValuesRepository.saveFuelCode(driverId)
    }
    fun saveQuantity(quantity:Double){
        saveTransactionValuesRepository.saveQuantity(quantity)
    }
    fun saveCardNumber(cardNumber:Int){
        saveTransactionValuesRepository.saveCardNumber(cardNumber)
    }

    fun saveTransactionType(transactionType:String){
        saveTransactionValuesRepository.saveTransactionType(transactionType)
    }
    fun saveInYardId(yardId:Int){
        saveTransactionValuesRepository.saveInYardId(yardId)
    }

    fun saveTransactionDto(transactionDto:TransactionDto){
        saveTransactionValuesRepository.saveTransactionDto(transactionDto)
    }
    fun saveVinNumber(vinNumber:Int){
    }

}