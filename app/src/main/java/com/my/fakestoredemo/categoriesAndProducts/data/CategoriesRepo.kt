package com.my.fakestoredemo.categoriesAndProducts.data

import android.util.Log
import com.my.fakestoredemo.common.network.ErrorConstant
import com.my.fakestoredemo.common.network.ResponseHandler
import com.my.fakestoredemo.common.network.getError
import retrofit2.HttpException
import java.io.IOException

class CategoriesRepo(private val categoriesApi: CategoriesApi) {
    suspend fun getCategories():ResponseHandler<List<String>>{
        return try {
            val response = categoriesApi.getProductsCategories()
            ResponseHandler.Success(response)
        } catch (e: HttpException) {
            Log.e("getCategories:HttpException-->", e.message())
            // HTTP exception handling: Wrap message in Failure
            ResponseHandler.Failure(
                message = e.response()?.errorBody()?.getError() ?: ErrorConstant.ErrorParsingExp
            )
        } catch (e: IOException) {
            Log.e("getCategories:IOException-->", "${e.message}")
            // Network/IO exception handling: Wrap network error message in Failure
            ResponseHandler.Failure(ErrorConstant.NetworkErrorMessage)
        } catch (e: Exception) {
            Log.e("getCategories:Exp-->", e.localizedMessage)
            // General exception handling: Wrap unknown error message in Failure
            ResponseHandler.Failure(e.localizedMessage)

        }
    }

}