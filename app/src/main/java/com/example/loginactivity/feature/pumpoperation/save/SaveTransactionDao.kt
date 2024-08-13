package com.example.loginactivity.feature.pumpoperation.save

interface SaveTransactionDao {

    suspend fun saveTransaction()
}