package com.my.fakestoredemo.categoriesAndProducts.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products/category/{categoryName}")
    suspend fun getProductsByCategory(@Path("categoryName") categoryName: String): List<ProductRemote>


}