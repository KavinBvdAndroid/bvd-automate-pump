package com.example.loginactivity.core.base.generics

sealed class Resource <out T> {

    data class Success<out T>(val data :T?) : Resource<T>()
    data class Failure(val message: String, val errorBody: GenericBaseResponse? = null) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data object Idle : Resource<Nothing>()

}