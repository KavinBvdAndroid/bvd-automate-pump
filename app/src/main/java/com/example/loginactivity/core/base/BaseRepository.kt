package com.example.loginactivity.core.base

import com.example.loginactivity.core.base.generics.GenericErrorResponse
import com.example.loginactivity.core.base.generics.Resource
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.Response

abstract class BaseRepository(private val gson: Gson) {


    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<ResponseBody>,
        successType: Class<T>,
        handleSuccess: (JsonObject) -> Unit = {}
    ): Resource<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.let {

                    val reader = it.charStream()
                    val jsonObject: JsonObject =
                        JsonParser().parse(reader).getAsJsonObject()

                    if (jsonObject.has("error") && jsonObject.get("error").asBoolean) {
                        val errorResponse =
                            gson.fromJson(jsonObject, GenericErrorResponse::class.java)
                        Resource.Failure(
                            errorResponse.message ?: "Unknown error",
                            errorResponse
                        )
                    } else {
                        handleSuccess(jsonObject)
                        val parsedResponse = gson.fromJson(jsonObject, successType)
                        Resource.Success(parsedResponse)
                    }

                } ?: Resource.Failure("Response Body is Empty")
            } else {
                Resource.Failure(response.message())
            }
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "An Unknown Error")
        } catch (io: IOException) {
            Resource.Failure(io.message ?: "An Unknown Error")
        }
    }
}