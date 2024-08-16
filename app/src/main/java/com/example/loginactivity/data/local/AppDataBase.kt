package com.example.loginactivity.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDao
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionEntity

@Database(entities = [SaveTransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun transactionDao(): SaveTransactionDao
}