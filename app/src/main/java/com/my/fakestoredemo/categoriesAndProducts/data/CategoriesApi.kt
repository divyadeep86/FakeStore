package com.my.fakestoredemo.categoriesAndProducts.data

import retrofit2.http.GET

interface CategoriesApi {
    @GET("products/categories")
    suspend fun getProductsCategories(): List<String>
}