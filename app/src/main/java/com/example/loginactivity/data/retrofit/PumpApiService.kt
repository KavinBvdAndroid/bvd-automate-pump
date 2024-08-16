package com.example.loginactivity.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface PumpApiService {

    @POST("/mplite_restapi.pb")
    suspend fun operatePump( @Query("cmd") command:String,
                             @Query("params") parameters: String) : Response<ResponseBody>
}