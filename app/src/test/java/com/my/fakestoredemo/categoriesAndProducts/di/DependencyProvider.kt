package com.my.fakestoredemo.categoriesAndProducts.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.my.fakestoredemo.categoriesAndProducts.data.CategoriesApi
import com.my.fakestoredemo.categoriesAndProducts.data.CategoriesRepo
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

class DependencyProvider {

     lateinit var mockWebServer: MockWebServer

     lateinit var categoriesApi: CategoriesApi

     lateinit var categoriesRepo: CategoriesRepo
    @OptIn(ExperimentalSerializationApi::class)
    fun build(){
        mockWebServer = MockWebServer()
        val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            coerceInputValues = true
        }
        categoriesApi = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
            .create(CategoriesApi::class.java)

        categoriesRepo = CategoriesRepo(categoriesApi)
    }
}