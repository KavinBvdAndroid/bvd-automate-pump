package com.example.bvddriverfleetapp.feature.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bvddriverfleetapp.core.utils.isValidEmail
import com.example.bvddriverfleetapp.data.retrofit.Resource
import com.example.bvddriverfleetapp.feature.auth.data.model.LoginResponse
import com.example.bvddriverfleetapp.feature.auth.domain.usecase.ValidateUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: ValidateUserEmailUseCase
) : ViewModel() {

    private val _userDetails = MutableLiveData<Resource<LoginResponse>>()
    val userDetails: LiveData<Resource<LoginResponse>> = _userDetails

    fun validateEmail(email: String): Boolean {
        return email.isValidEmail()
    }

    fun loginUserEmail(email: String) {
        viewModelScope.launch {
            _userDetails.value = Resource.Loading
            val result = useCase.verifyUserEmail(email)
            _userDetails.value = result
        }
    }


}