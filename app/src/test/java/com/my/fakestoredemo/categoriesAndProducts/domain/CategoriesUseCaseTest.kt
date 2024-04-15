package com.my.fakestoredemo.categoriesAndProducts.domain


import com.my.fakestoredemo.categoriesAndProducts.di.DependencyProvider
import com.my.fakestoredemo.common.utils.Helper

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CategoriesUseCaseTest {


lateinit var dependencyProvider: DependencyProvider




    @Before
    fun setUp() {
        dependencyProvider = DependencyProvider()
        dependencyProvider.build()

    }

    @Test
    fun test_getCategory_Success_True_Excepted() = runTest {
        val mockResponse = MockResponse()
        val testResponse = Helper.readFileResource("/categoriesListResponse.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(testResponse)
        dependencyProvider.mockWebServer.enqueue(mockResponse)
val categoriesUseCase = CategoriesUseCase(dependencyProvider.categoriesRepo)
        val dataState = categoriesUseCase.getCategories().toList()

        Assert.assertEquals(true, dataState[0].isLoading)
        Assert.assertEquals(true, dataState[1].data != null)
    }

    @Test
    fun test_getCategory_Failure_True_Excepted() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(403)
        dependencyProvider.mockWebServer.enqueue(mockResponse)
        val categoriesUseCase = CategoriesUseCase(dependencyProvider.categoriesRepo)
        val dataState = categoriesUseCase.getCategories().toList()
        Assert.assertEquals(true, dataState[0].isLoading)
        Assert.assertEquals(true, dataState[1].errorMessage != null)

    }

    @After
    fun tearDown() {
        dependencyProvider.mockWebServer.shutdown()
    }
}