package com.example.loginactivity.feature.pumpoperation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import com.example.loginactivity.feature.pumpoperation.domain.usecase.SaveTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val saveTransactionUseCase: SaveTransactionUseCase): ViewModel() {

    private val _saveTransactionResponse = MutableLiveData<Resource<GenericBaseResponse>>()
    val saveTransactionResponse : LiveData<Resource<GenericBaseResponse>> = _saveTransactionResponse

    fun saveTransaction(request: SaveTransactionDto){
        viewModelScope.launch {
            Resource.Loading
            val result = saveTransactionUseCase.saveTransaction(request)
            _saveTransactionResponse.value = result
        }
    }

    fun saveTransaction_MockData(request: SaveTransactionDto){
        viewModelScope.launch {
            Resource.Loading
            delay(2000)
            _saveTransactionResponse.value = Resource.Success(GenericBaseResponse(200,"Record Created Successfully", error = false))
        }
    }
}