package com.example.loginactivity.feature.pumpoperation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.startMockPumpResponse
import com.example.loginactivity.core.base.generics.stopMockPumpResponse
import com.example.loginactivity.feature.pumpoperation.data.model.PumpResponse
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionSaveResult
import com.example.loginactivity.feature.pumpoperation.data.model.TransactionState
import com.example.loginactivity.feature.pumpoperation.domain.usecase.SaveTransactionUseCase
import com.example.loginactivity.feature.pumpoperation.domain.usecase.StartPumpUseCase
import com.example.loginactivity.feature.pumpoperation.domain.usecase.StopPumpUseCase
import com.example.loginactivity.feature.pumpoperation.save.SaveTransactionDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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
    val pumpStopLivedata: MutableLiveData<Resource<PumpResponse>> = _pumpStopLivedata

    private val _saveTransactionLivedata = MutableStateFlow<TransactionState>(TransactionState.Idle)
    val saveTransactionLivedata: MutableStateFlow<TransactionState> = _saveTransactionLivedata

    fun saveTransaction(request: SaveTransactionDto) {
        viewModelScope.launch {
            _saveTransactionLivedata.value = TransactionState.Loading
            val result = saveTransactionUseCase.saveTransactionRemote(request)
            _saveTransactionLivedata.value = when (result) {
                is TransactionSaveResult.Success -> TransactionState.Success(
                    "Transaction saved successfully",
                    result.savedTransactionDto
                )

                is TransactionSaveResult.ApiFailureLocalSuccess -> TransactionState.PartialSuccess(
                    "Saved locally, but API sync failed: ${result.apiError}",
                    result.SaveTransactionDto
                )

                is TransactionSaveResult.ApiSuccessLocalFailure -> TransactionState.Error("API sync successful, but local save failed: ${result.localError}")
                is TransactionSaveResult.BothFailed -> TransactionState.Error("Both API and local save failed. API: ${result.apiError}, Local: ${result.localError}")
            }
        }
    }

//    fun saveTransaction_MockData(request: SaveTransactionDto) {
//        viewModelScope.launch {
//            Resource.Loading
//            delay(2000)
//            _saveTransactionLivedata.value = Resource.Success(
//                GenericBaseResponse(
//                    200,
//                    "Record Created Successfully",
//                    error = false
//                )
//            )
//        }
//    }


    fun startPump(cmd: String, params: String) {
        viewModelScope.launch {
            _pumpStartLivedata.value = Resource.Loading
//           val actualResult = pumpStartUseCase.startActivatingPump(cmd,params)
            delay(1000)
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
