package com.example.loginactivity.core.base

import android.util.Log
import com.example.loginactivity.core.base.generics.GenericBaseResponse
import com.example.loginactivity.core.base.generics.Resource
import com.example.loginactivity.feature.pumpoperation.data.model.save.TransactionDto
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.Response
import java.lang.reflect.Type

abstract class BaseRepository(private val gson: Gson) {


    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<ResponseBody>,
        successType: Class<T>,
        handleSuccess: (JsonObject) -> Unit = {},
        handleFailure: (String) -> Unit
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
                            gson.fromJson(jsonObject, GenericBaseResponse::class.java)
                        handleFailure(errorResponse.message.toString())
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
                Log.d("Error body", "" + response.errorBody().toString())
                handleFailure(response.message())
                Resource.Failure(response.message())
            }
        } catch (e: Exception) {
            handleFailure(e.message ?: "An Unknown Error")
            Resource.Failure(e.message ?: "An Unknown Error")
        } catch (io: IOException) {
            handleFailure(io.message ?: "An Unknown Error")
            Resource.Failure(io.message ?: "An Unknown Error")
        }
    }

    protected suspend fun <T> safeApiCallList(
        apiCall: suspend () -> Response<ResponseBody>,
        successType: Type,
        handleSuccess: (JsonObject) -> Unit = {},
        handleFailure: (String) -> Unit
    ): Resource<List<T>> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.let {
                    val reader = it.charStream()
                    val jsonObject: JsonObject =
                        JsonParser().parse(reader).getAsJsonObject()

                    if (jsonObject.has("error") && jsonObject.get("error").asBoolean) {
                        val errorResponse =
                            gson.fromJson(jsonObject, GenericBaseResponse::class.java)
                        handleFailure(errorResponse.message.toString())
                        Resource.Failure(
                            errorResponse.message ?: "Unknown error",
                            errorResponse
                        )

                    } else {
                        handleSuccess(jsonObject)
                        val parsedResponse = gson.fromJson(
                            jsonObject,
                            TypeToken.getParameterized(List::class.java, successType).type
                        ) as List<T>
                        Resource.Success(parsedResponse)
                    }

                } ?: Resource.Failure("Response Body is Empty")
            } else {
                handleFailure(response.message())
                Resource.Failure(response.message())
            }
        } catch (e: Exception) {
            handleFailure(e.message ?: "An Unknown Error")
            Resource.Failure(e.message ?: "An Unknown Error")
        } catch (io: IOException) {
            handleFailure(io.message ?: "An Unknown Error")
            Resource.Failure(io.message ?: "An Unknown Error")
        }
    }

    protected suspend fun <T> safeApiCallList2(
        apiCall: suspend () -> Response<ResponseBody>,
        successType: Class<T>,
        handleSuccess: (JsonObject) -> Unit = {},
        handleFailure: (String) -> Unit
    ): Resource<List<T>> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.let {
                    val reader = it.charStream()
                    val jsonObject: JsonObject =
                        JsonParser().parse(reader).getAsJsonObject()

                    if (jsonObject.has("error") && jsonObject.get("error").asBoolean) {
                        val errorResponse =
                            gson.fromJson(jsonObject, GenericBaseResponse::class.java)
                        handleFailure(errorResponse.message.toString())
                        Resource.Failure(
                            errorResponse.message ?: "Unknown error",
                            errorResponse
                        )
                    } else {
                        handleSuccess(jsonObject)
                        val parsedResponse = gson.fromJson(
                            jsonObject,
                            TypeToken.getParameterized(List::class.java, successType).type
                        ) as List<T>
                        Resource.Success(parsedResponse)
                    }
                } ?: Resource.Failure("Response Body is Empty")
            } else {
                handleFailure(response.message())
                Resource.Failure(response.message())
            }
        } catch (e: Exception) {
            handleFailure(e.message ?: "An Unknown Error")
            Resource.Failure(e.message ?: "An Unknown Error")
        } catch (io: IOException) {
            handleFailure(io.message ?: "An Unknown Error")
            Resource.Failure(io.message ?: "An Unknown Error")
        }
    }
}