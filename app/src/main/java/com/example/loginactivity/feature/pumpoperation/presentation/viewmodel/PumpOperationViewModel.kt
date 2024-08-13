package com.example.loginactivity.feature.pumpoperation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.startMockPumpResponse
import com.example.loginactivity.core.base.generics.stopMockPumpResponse
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import com.example.loginactivity.feature.pumpoperation.domain.usecase.SaveTransactionUseCase
import com.example.loginactivity.feature.pumpoperation.domain.usecase.StartPumpUseCase
import com.example.loginactivity.feature.pumpoperation.domain.usecase.StopPumpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PumpOperationViewModel @Inject constructor(
    private val pumpStartUseCase: StartPumpUseCase,
    private val pumpStopUseCase: StopPumpUseCase,
    private val saveTransactionUseCase: SaveTransactionUseCase
) : ViewModel() {

    private val _pumpStartLivedata = MutableLiveData<Resource<PumpResponse>>()
    val pumpStartLivedata: LiveData<Resource<PumpResponse>> = _pumpStartLivedata

    private val _pumpStopLivedata = MutableLiveData<Resource<PumpResponse>>()
    val pumpStopLivedata: LiveData<Resource<PumpResponse>> = _pumpStopLivedata

    private val _saveTransactionResponse = MutableLiveData<Resource<GenericBaseResponse>>()
    val saveTransactionResponse: LiveData<Resource<GenericBaseResponse>> = _saveTransactionResponse

    fun saveTransaction(request: SaveTransactionDto) {
        viewModelScope.launch {
            Resource.Loading
            val result = saveTransactionUseCase.saveTransaction(request)
            _saveTransactionResponse.value = result
        }
    }

    fun saveTransaction_MockData(request: SaveTransactionDto) {
        viewModelScope.launch {
            Resource.Loading
            delay(2000)
            _saveTransactionResponse.value = Resource.Success(
                GenericBaseResponse(
                    200,
                    "Record Created Successfully",
                    error = false
                )
            )
        }

        fun startPump(cmd: String, params: String) {
            viewModelScope.launch {
                _pumpStartLivedata.value = Resource.Loading
//           val actualResult = pumpStartUseCase.startActivatingPump(cmd,params)
                delay(5000)
                val result = Resource.Success(startMockPumpResponse)
                val Fresult = Resource.Failure("Pump Failed to turn on")

                _pumpStartLivedata.value = result
            }
        }

        fun stopPump(cmd: String, params: String) {
            viewModelScope.launch {
                _pumpStopLivedata.value = Resource.Loading
                delay(5000)
//            val actualResult = pumpStopUseCase.stopActivatingPump(cmd,params)
                val result = Resource.Success(stopMockPumpResponse)
                val Fresult = Resource.Failure("Pump Failed to turn off")
                _pumpStopLivedata.value = result
            }
        }

    }
}