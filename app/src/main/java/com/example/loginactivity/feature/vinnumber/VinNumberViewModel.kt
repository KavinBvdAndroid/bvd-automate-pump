package com.example.loginactivity.feature.vinnumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.isValidEmail
import com.example.loginactivity.feature.auth.data.model.LoginResponse
import com.example.loginactivity.feature.auth.domain.ValidateUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VinNumberViewModel @Inject constructor(
    private val useCase: ValidateVinNumberUseCase
) : ViewModel() {

    private val _vehicleDetails = MutableLiveData<Resource<VinNumberResponse>>()
    val vehicleDetails: LiveData<Resource<VinNumberResponse>> = _vehicleDetails



    fun verifyVinNumber(vinNumber: String) {
        viewModelScope.launch {
            _vehicleDetails.value = Resource.Loading
            val result = useCase.verifyVinNumber(vinNumber)
//            delay(2000)
//            val result = Resource.Success(VinNumberResponse())
            _vehicleDetails.value = result
        }
    }


}