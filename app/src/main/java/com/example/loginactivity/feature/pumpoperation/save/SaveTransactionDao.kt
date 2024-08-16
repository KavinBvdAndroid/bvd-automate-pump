package com.example.loginactivity.feature.pumpoperation.save

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SaveTransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(saveTransactionEntity: SaveTransactionEntity)

    @Query("SELECT * FROM transactions where id = :id")
    suspend fun getTransactionById(id: Int): SaveTransactionEntity

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<SaveTransactionEntity>
}