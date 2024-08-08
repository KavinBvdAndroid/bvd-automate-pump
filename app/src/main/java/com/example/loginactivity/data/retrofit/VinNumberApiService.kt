package com.example.loginactivity.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VinNumberApiService {
    @GET("api/getTankerDetails")
    suspend fun validateVinNumber(
        @Query("vin_number") vinNumber:String,
    ) : Response<ResponseBody>
}