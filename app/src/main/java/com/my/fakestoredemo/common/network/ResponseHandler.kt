package com.my.fakestoredemo.common.network

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class ResponseHandler<out T>{
    data class Success<out T>(val data: T) : ResponseHandler<T>()
    data class Failure(val message:String) : ResponseHandler<Nothing>()
}

suspend fun <T> Response<T>.handleNetworkResponse(): ResponseHandler<T> {
    return try {
        if (this.isSuccessful && this.body() != null) {
            ResponseHandler.Success(this.body()!!)
        } else {
            ResponseHandler.Failure(
                message = this.errorBody()?.getError() ?: ErrorConstant.ErrorParsingExp
            )
        }
    } catch (e: HttpException) {
        Log.e("HttpException-->", e.message())
        ResponseHandler.Failure(
            message = e.response()?.errorBody()?.getError() ?: ErrorConstant.ErrorParsingExp
        )
    } catch (e: IOException) {
        Log.e("IOException-->", "${e.message}")
        ResponseHandler.Failure(ErrorConstant.NetworkErrorMessage)
    } catch (e: Exception) {
        Log.e("Exp-->", e.localizedMessage)
        ResponseHandler.Failure(e.localizedMessage)
    }
}