package com.example.loginactivity.feature.pumpoperation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.loginactivity.feature.maps.data.model.InyardTanksItem
import com.example.loginactivity.feature.transaction.domain.usecases.SaveTransactionValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FuelSelectionViewModel @Inject constructor(private val saveTransactionValuesUseCase: SaveTransactionValuesUseCase) :
    ViewModel() {

    fun storeTransactionDetails(transactionDto: InyardTanksItem) {
        saveTransactionValuesUseCase.saveTransactionDto(transactionDto)
    }
}