package com.example.loginactivity.feature.pumpoperation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.domain.usecases.SaveTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val saveTransactionUseCase: SaveTransactionUseCase): ViewModel() {

    private val _saveTransactionResponse = MutableLiveData<Resource<GenericBaseResponse>>()
    val saveTransactionResponse : LiveData<Resource<GenericBaseResponse>> = _saveTransactionResponse

    fun saveTransaction(request: TransactionDto){
        viewModelScope.launch {
//            Resource.Loading
//            val result = saveTransactionUseCase.saveTransactionRemote(request)
//            _saveTransactionResponse.value = result
        }
    }

    fun saveTransaction_MockData(request: TransactionDto){
//        viewModelScope.launch {
//            Resource.Loading
//            delay(2000)
//            _saveTransactionResponse.value = Resource.Success(GenericBaseResponse(200,"Record Created Successfully", error = false))
//        }
    }
}