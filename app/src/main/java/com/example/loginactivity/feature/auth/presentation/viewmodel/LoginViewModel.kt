package com.example.loginactivity.feature.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.core.base.generics.isValidEmail
import com.example.loginactivity.feature.auth.data.model.LoginResponse
import com.example.loginactivity.feature.auth.domain.ValidateUserEmailUseCase
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

    fun loginUserEmail(email: String,password:String) {
        viewModelScope.launch {
            _userDetails.value = Resource.Loading
            val result = useCase.verifyUserEmail(email, password)
            _userDetails.value = result
        }
    }


}