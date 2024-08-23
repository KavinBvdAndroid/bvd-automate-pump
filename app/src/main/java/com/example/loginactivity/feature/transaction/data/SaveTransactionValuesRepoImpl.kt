package com.example.loginactivity.feature.transaction.data

import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.utils.Constants
import com.example.loginactivity.feature.maps.data.model.DataItem
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.domain.SaveTransactionValuesRepository
import com.google.gson.Gson
import javax.inject.Inject

class SaveTransactionValuesRepoImpl @Inject constructor(private val sessionManager: SessionManager) :
    SaveTransactionValuesRepository {
    override fun saveDriverId(driverId: String) {
        sessionManager.saveString(Constants.DRIVER_ID,driverId)
    }

    override fun saveTruckId(truckId: String) {
        sessionManager.saveString(Constants.TRUCK_ID,truckId)
    }

    override fun saveFuelCode(fuelCode: String) {
        sessionManager.saveString(Constants.FUEL_CODE,fuelCode)
    }

    override fun saveQuantity(qty: Double) {
        sessionManager.saveDouble(Constants.QUANTITY,qty)
    }

    override fun saveTransactionType(type: String) {
        sessionManager.saveString(Constants.TRANSACTION_TYPE,type)
    }

    override fun saveInYardId(yardId: Int) {
        sessionManager.saveInt(Constants.YARD_ID,yardId)
    }

    override fun saveCardNumber(cardNumber: Int) {
        sessionManager.saveInt(Constants.CARD_NUMBER,cardNumber)
    }

    override fun saveTransactionDto(transactionDto: DataItem) {
        val userJson = Gson().toJson(transactionDto)

        sessionManager.saveTransactionDto(userJson)
    }
}