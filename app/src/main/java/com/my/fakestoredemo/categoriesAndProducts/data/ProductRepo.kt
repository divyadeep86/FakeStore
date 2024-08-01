package com.my.fakestoredemo.categoriesAndProducts.data

import android.util.Log

import com.my.fakestoredemo.common.network.ErrorConstant
import com.my.fakestoredemo.common.network.ResponseHandler
import com.my.fakestoredemo.common.network.getError
import retrofit2.HttpException
import java.io.IOException

class ProductRepo(private val productApi: ProductApi) {

    suspend fun getProductByCategory(categoryName: String): ResponseHandler<List<ProductRemote>> {
        return try {

            val response = productApi.getProductsByCategory(categoryName)
            ResponseHandler.Success(response)

        } catch (e: HttpException) {

            Log.e("getProductByCategory:HttpException-->", e.message())
            // HTTP exception handling: Wrap message in Failure
            ResponseHandler.Failure(
                message = e.response()?.errorBody()?.getError() ?: ErrorConstant.ErrorParsingExp
            )

        } catch (e: IOException) {

            Log.e("getProductByCategory:IOException-->", "${e.message}")
            // Network/IO exception handling: Wrap network error message in Failure
            ResponseHandler.Failure(ErrorConstant.NetworkErrorMessage)

        } catch (e: Exception) {

            Log.e("getProductByCategory:Exp-->", e.localizedMessage)
            // General exception handling: Wrap unknown error message in Failure
            ResponseHandler.Failure(e.localizedMessage)


        }
    }
}