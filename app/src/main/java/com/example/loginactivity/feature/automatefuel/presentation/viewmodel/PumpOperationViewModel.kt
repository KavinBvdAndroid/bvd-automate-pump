package com.example.loginactivity.feature.automatefuel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.automatefuel.data.model.PumpResponse
import com.example.loginactivity.feature.automatefuel.domain.usecase.StartPumpUseCase
import com.example.loginactivity.feature.automatefuel.domain.usecase.StopPumpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PumpOperationViewModel @Inject constructor(
    private val pumpStartUseCase: StartPumpUseCase,
    private val pumpStopUseCase: StopPumpUseCase
) : ViewModel() {

    private val _pumpResponseLivedata = MutableLiveData<Resource<PumpResponse>>()
    val pumpResponseLivedata: LiveData<Resource<PumpResponse>> = _pumpResponseLivedata

    fun  startPump(cmd: String,params:String){
        viewModelScope.launch {
            _pumpResponseLivedata.value = Resource.Loading
           val result = pumpStartUseCase.startActivatingPump(cmd,params)
            _pumpResponseLivedata.value= result
        }
    }

    fun stopPump(cmd: String,params:String){
        viewModelScope.launch {
            _pumpResponseLivedata.value = Resource.Loading
            val result = pumpStopUseCase.stopActivatingPump(cmd,params)
            _pumpResponseLivedata.value= result
        }
    }

}