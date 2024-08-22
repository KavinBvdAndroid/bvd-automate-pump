package com.example.loginactivity.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginactivity.feature.pumpoperation.data.model.save.SaveTransactionDao
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun transactionDao(): SaveTransactionDao
}