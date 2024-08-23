package com.example.loginactivity.feature.pumpoperation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.domain.usecases.SaveTransactionUseCase
import com.example.loginactivity.feature.transaction.domain.usecases.SaveTransactionValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val saveTransactionValuesUseCase: SaveTransactionValuesUseCase) :
    ViewModel() {

    private val _saveTransactionResponse = MutableLiveData<Resource<GenericBaseResponse>>()
    val saveTransactionResponse: LiveData<Resource<GenericBaseResponse>> = _saveTransactionResponse

    fun saveTransaction(request: TransactionDto) {
        viewModelScope.launch {
            request.let {
                it.fuelCode?.let { it1 -> saveTransactionValuesUseCase.saveFuelCode(it1) }
                it.quantity?.let { it1 -> saveTransactionValuesUseCase.saveQuantity(it1) }
                it.transactionType?.let { it1 ->
                    saveTransactionValuesUseCase.saveTransactionType(
                        it1
                    )
                }
                it.cardNumber?.let { it1 -> saveTransactionValuesUseCase.saveCardNumber(it1) }
                it.inyardSiteId?.let { it1 -> saveTransactionValuesUseCase.saveInYardId(it1) }
            }
        }
    }

    fun saveTransaction_MockData(request: TransactionDto) {
//        viewModelScope.launch {
//            Resource.Loading
//            delay(2000)
//            _saveTransactionResponse.value = Resource.Success(GenericBaseResponse(200,"Record Created Successfully", error = false))
//        }
    }
}