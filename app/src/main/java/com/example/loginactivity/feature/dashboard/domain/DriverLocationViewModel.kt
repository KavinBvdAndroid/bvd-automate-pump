package com.example.loginactivity.feature.dashboard.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.example.loginactivity.feature.transaction.domain.usecases.GetSavedTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverLocationViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getSavedTransaction: GetSavedTransactionUseCase
) : ViewModel() {

    private val _savedTransactions = MutableStateFlow<Resource<List<TransactionDto>>>(Resource.Idle)
    val savedTransactions: StateFlow<Resource<List<TransactionDto>>> = _savedTransactions


    fun getAllTransactions() {
        Resource.Loading
        viewModelScope.launch {
            sessionManager.getDriverId()?.let {
                val result = getSavedTransaction.getSavedTransactions(it.toInt())
                _savedTransactions.value = result
            }

        }
    }

    fun clearSharedPref(): Boolean {
        return sessionManager.clearSharedPref()
    }

}