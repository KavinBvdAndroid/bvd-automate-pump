package com.example.loginactivity.data.retrofit

import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {
    @POST("api/verifyUser")
    suspend fun loginUser(
        @Query("username") userName:String,
        @Query("password") password:String
    ) : Response<ResponseBody>

    @GET("api/getTankerDetails")
    suspend fun validateVinNumber(
        @Query("vin_number") vinNumber:String,
    ) : Response<ResponseBody>


    @POST("api/fetchInyardSites")
    suspend fun fetchInYardItems(): Response<ResponseBody>


    @POST("api/saveInyardTransaction")
    suspend fun saveInYardTransactions(
        @Body transactionDetails: TransactionDto
    ): Response<ResponseBody>
}