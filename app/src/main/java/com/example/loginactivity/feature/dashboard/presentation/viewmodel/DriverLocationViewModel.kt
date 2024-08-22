package com.example.loginactivity.feature.dashboard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.data.sharedpref.LoggingSharedPrefMethods
import com.example.loginactivity.feature.maps.domain.usecase.GetAllTransactionsUseCase
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverLocationViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
) : ViewModel(),
    LoggingSharedPrefMethods {


    private val _transactionsMutableLiveData = MutableLiveData<Resource<List<TransactionDto>>>()
    private val transactionLiveData: LiveData<Resource<List<TransactionDto>>> =
        _transactionsMutableLiveData

//    private val _saveTransactionLivedata = MutableStateFlow<TransactionState>(TransactionState.Idle)
//    val saveTransactionLivedata: MutableStateFlow<TransactionState> = _saveTransactionLivedata

    override fun clearSharedPref(): Boolean {
        return sessionManager.clearSharedPref()
    }

    fun getAllTransactions() {
        Resource.Loading
        viewModelScope.launch {
            _transactionsMutableLiveData.value =
                sessionManager.getDriverId()
                    ?.let { getAllTransactionsUseCase.getAllTransactions(it.toInt(), sessionManager.getTruckId()!!
                        .toInt()) }
        }
    }


}