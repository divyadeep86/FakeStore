package com.my.fakestoredemo.categoriesAndProducts.data

import com.my.fakestoredemo.categoriesAndProducts.di.DependencyProvider
import com.my.fakestoredemo.common.network.ErrorConstant
import com.my.fakestoredemo.common.network.ResponseHandler
import com.my.fakestoredemo.common.utils.Helper
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class CategoriesRepoTest {
  lateinit var dependencyProvider: DependencyProvider

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setUp() {
        dependencyProvider = DependencyProvider()
      dependencyProvider.build()
    }

    @Test
    fun test_getCatogories_empty_response_true_expected() = runTest{
        val mockRespose = MockResponse()
        mockRespose.setResponseCode(200)
        mockRespose.setBody("[]")
        dependencyProvider.mockWebServer.enqueue(mockRespose)
        val response = dependencyProvider.categoriesRepo.getCategories()
        dependencyProvider.mockWebServer.takeRequest()

        Assert.assertEquals(true, (response as ResponseHandler.Success).data.isEmpty())
    }

    @Test
    fun test_getCategories_response_true_expected() = runTest {
        val mockRespose = MockResponse()
          val testResponse = Helper.readFileResource("/categoriesListResponse.json")
        mockRespose.setResponseCode(200)
        mockRespose.setBody(testResponse)
        dependencyProvider.mockWebServer.enqueue(mockRespose)
        val response = dependencyProvider.categoriesRepo.getCategories()
        dependencyProvider.mockWebServer.takeRequest()
        Assert.assertEquals(true, response is ResponseHandler.Success && response.data.isNotEmpty())
    }

    @Test
    fun test_getCategories_HttpException_expected_Failure()= runTest {
        val mockRespose = MockResponse()
        val testResponse = Helper.readFileResource("/httpexception_response.json")
        mockRespose.setResponseCode(403)
        mockRespose.setBody(testResponse)
        dependencyProvider.mockWebServer.enqueue(mockRespose)
        val response = dependencyProvider.categoriesRepo.getCategories()
        dependencyProvider.mockWebServer.takeRequest()
        Assert.assertEquals(true, response is ResponseHandler.Failure)
    }
    @Test
    fun test_getCategories_IOException_expected_Failure_with_NetwrokErrorMessage()= runTest {
//for network error
// Shutdown MockWebServer to simulate a network error
        dependencyProvider.mockWebServer.shutdown()
        val response = dependencyProvider.categoriesRepo.getCategories()
        Assert.assertEquals(true, response is ResponseHandler.Failure && response.message == ErrorConstant.NetworkErrorMessage)

    }
    @Test(expected = Exception::class)
    fun test_getCategories_Exception_expected_Failure_with_ExceptionMessage ()  = runTest {
//for
        // Mock CategoriesRepo
        val categoriesRepo = Mockito.mock(CategoriesRepo::class.java)
        Mockito.`when`( categoriesRepo.getCategories()).thenThrow(Exception("Unknown error"))
        val response = categoriesRepo.getCategories()
        Assert.assertEquals(true,
            response is ResponseHandler.Failure && response.message == "Unknown error"
        )
    }
    @Test
    fun test_getCategories_WithMalformedJSON_Exception_expected_Failure_with_ExceptionMessage ()  = runTest {
        val mockRespose = MockResponse()
        val testResponse = Helper.readFileResource("/malsformedJson.json")
        mockRespose.setResponseCode(200)
        mockRespose.setBody(testResponse)
        dependencyProvider.mockWebServer.enqueue(mockRespose)
        val response = dependencyProvider.categoriesRepo.getCategories()
        dependencyProvider.mockWebServer.takeRequest()
        Assert.assertEquals(true, response is ResponseHandler.Failure)
    }

    @After
    fun tearDown() {
        dependencyProvider.mockWebServer.shutdown()
    }
}