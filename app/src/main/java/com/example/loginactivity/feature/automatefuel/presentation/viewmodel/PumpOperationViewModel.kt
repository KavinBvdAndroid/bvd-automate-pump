package com.example.loginactivity.feature.automatefuel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.startMockPumpResponse
import com.example.loginactivity.core.base.generics.stopMockPumpResponse
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse
import com.example.loginactivity.feature.automatefuel.domain.usecase.StartPumpUseCase
import com.example.loginactivity.feature.automatefuel.domain.usecase.StopPumpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PumpOperationViewModel @Inject constructor(
    private val pumpStartUseCase: StartPumpUseCase,
    private val pumpStopUseCase: StopPumpUseCase
) : ViewModel() {

    private val _pumpStartLivedata = MutableLiveData<Resource<PumpResponse>>()
    val pumpStartLivedata: LiveData<Resource<PumpResponse>> = _pumpStartLivedata

    private val _pumpStopLivedata = MutableLiveData<Resource<PumpResponse>>()
    val pumpStopLivedata: LiveData<Resource<PumpResponse>> = _pumpStopLivedata

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