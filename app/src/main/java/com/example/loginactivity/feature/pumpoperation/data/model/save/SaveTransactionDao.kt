package com.example.loginactivity.feature.pumpoperation.data.model.save

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SaveTransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(saveTransactionEntity: TransactionEntity)

    @Query("SELECT * FROM transactions where id = :id")
    suspend fun getTransactionById(id: Int): TransactionEntity

    @Query("SELECT * FROM Transactions WHERE driver_id = :driverId")
    suspend fun getAllTransactions(driverId:Int): List<TransactionEntity>
}