package com.my.fakestoredemo.categoriesAndProducts.ui

import app.cash.turbine.test
import com.my.fakestoredemo.categoriesAndProducts.di.DependencyProvider
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoriesUseCase
import com.my.fakestoredemo.common.utils.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoriesViewModelTest {

    lateinit var dependencyProvider: DependencyProvider
    lateinit var categoriesUseCase: CategoriesUseCase
    lateinit var categoriesViewModel: CategoriesViewModel

 /*   @get:Rule
    val rule = InstantTaskExecutorRule()*/

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        dependencyProvider = DependencyProvider()
        dependencyProvider.build()
        val mockResponse = MockResponse()
        val testResponse = Helper.readFileResource("/categoriesListResponse.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(testResponse)
        dependencyProvider.mockWebServer.enqueue(mockResponse)


    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_dataStates_loading_success_expected_true() = runTest {
        categoriesUseCase = CategoriesUseCase(dependencyProvider.categoriesRepo)
        categoriesViewModel = CategoriesViewModel(categoriesUseCase)
        advanceUntilIdle()
        categoriesViewModel.viewState.test {
            val dataState = awaitItem()
            Assert.assertEquals(true, dataState.isLoading && dataState.dataState==null)
            val dataState2 = awaitItem()
            Assert.assertEquals(true, dataState2.dataState!=null && !dataState2.isLoading)
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        dependencyProvider.mockWebServer.shutdown()
        Dispatchers.resetMain()
    }
}